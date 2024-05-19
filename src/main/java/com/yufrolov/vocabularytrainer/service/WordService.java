package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.dto.LibreTranslateDTO;
import com.yufrolov.vocabularytrainer.dto.TranslateDTO;
import com.yufrolov.vocabularytrainer.entity.Translation;
import com.yufrolov.vocabularytrainer.entity.Vocabulary;
import com.yufrolov.vocabularytrainer.entity.Word;
import com.yufrolov.vocabularytrainer.repository.WordRepository;
import com.yufrolov.vocabularytrainer.service.thirdparty.LibreTranslateClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WordService {
    private final WordRepository wordRepository;

    private final VocabularyService vocabularyService;

    private final LanguageService languageService;

    private final TranslationService translationService;

    private final VocabularyTranslationService vocabularyTranslationService;

    private final LibreTranslateClient libreTranslateClient;

    public WordService(WordRepository wordRepository
            , VocabularyService vocabularyService
            , LanguageService languageService
            , TranslationService translationService
            , VocabularyTranslationService vocabularyTranslationService
            , LibreTranslateClient libreTranslateClient) {
        this.wordRepository = wordRepository;
        this.vocabularyService = vocabularyService;
        this.languageService = languageService;
        this.translationService = translationService;
        this.vocabularyTranslationService = vocabularyTranslationService;
        this.libreTranslateClient = libreTranslateClient;
    }

    private TranslateDTO translateExternal(TranslateDTO translateDTO, Vocabulary vocabulary) {
        languageService.search(vocabulary.getLanguageTranslateCode().getCode());
        return libreTranslateClient.translate(
                new LibreTranslateDTO(
                        translateDTO.getTranslatedText()
                        , vocabulary.getLanguageCode().getCode()
                        , vocabulary.getLanguageTranslateCode().getCode()
                        , "text"
                        , ""
                ));
    }

    private Word createOrGet(String text, String languageCode) {
        return wordRepository
                .findWordByTextAndLanguageCode(text, languageCode)
                .orElseGet(() -> wordRepository.save(new Word(text, languageService.search(languageCode))));
    }

    private void savingWordConnections(Word word, Word translateWord, Vocabulary vocabulary) {
        vocabularyTranslationService.create(
                vocabulary
                ,translationService.createOrGet(word, translateWord));
    }

    private Word findTranslateWord(Word word, String languageCode) {
        var result = word.getWordsToTranslate().stream()
                .map(Translation::getTranslateWord)
                .filter(it -> it.getLanguageCode().getCode().equals(languageCode))
                .findFirst().orElse(null);
        if (result != null) {
            return result;
        }
        return word.getTranslatedWords().stream()
                .map(Translation::getWord)
                .filter(it -> it.getLanguageCode().getCode().equals(languageCode))
                .findFirst().orElse(null);
    }


    public TranslateDTO translate(UUID profileId, TranslateDTO translateDTO, Long vocabularyId) {
        Word translateWord;
        Vocabulary vocabulary = vocabularyService.getVocabulary(profileId,vocabularyId);
        var word = createOrGet(translateDTO.getTranslatedText(), vocabulary.getLanguageCode().getCode());

        translateWord = findTranslateWord(word, vocabulary.getLanguageTranslateCode().getCode());

        var response = translateWord == null ? translateExternal(translateDTO, vocabulary) : new TranslateDTO(translateWord.getWord());

        translateWord = createOrGet(response.getTranslatedText(), vocabulary.getLanguageTranslateCode().getCode());

        savingWordConnections(word, translateWord, vocabulary);
        return response;
    }

}
