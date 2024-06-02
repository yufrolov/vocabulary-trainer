package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.dto.LibreTranslateDTO;
import com.yufrolov.vocabularytrainer.dto.TranslateDTO;
import com.yufrolov.vocabularytrainer.entity.Translation;
import com.yufrolov.vocabularytrainer.entity.Word;
import com.yufrolov.vocabularytrainer.repository.WordRepository;
import com.yufrolov.vocabularytrainer.service.thirdparty.LibreTranslateClient;
import org.springframework.stereotype.Service;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final LanguageService languageService;
    private final LibreTranslateClient libreTranslateClient;

    public WordService(WordRepository wordRepository
            , LanguageService languageService
            , LibreTranslateClient libreTranslateClient) {
        this.wordRepository = wordRepository;
        this.languageService = languageService;
        this.libreTranslateClient = libreTranslateClient;
    }

    private TranslateDTO translateExternal(TranslateDTO translateDTO, String langCode, String langTranslatedCode) {
        return libreTranslateClient.translate(
                new LibreTranslateDTO(
                        translateDTO.getTranslatedText()
                        , langCode
                        , langTranslatedCode
                        , "text"
                        , ""
                ));
    }

    public Word createOrGet(String text, String languageCode) {
        return wordRepository
                .findWordByTextAndLanguageCode(text, languageCode)
                .orElseGet(() -> wordRepository.save(new Word(text, languageService.search(languageCode))));
    }

    public Word findTranslateWord(Word word, String languageCode) {
        var result = word.getWordsToTranslate().stream()
                .map(Translation::getTranslateWord)
                .filter(it -> it.getLanguageCode().getCode().equals(languageCode))
                .findFirst().orElse(null);
        if (result != null) {
            return result;
        }
        result = word.getTranslatedWords().stream()
                .map(Translation::getWord)
                .filter(it -> it.getLanguageCode().getCode().equals(languageCode))
                .findFirst().orElse(null);

        if (result != null) {
            return result;
        }

        var translate = translateExternal(new TranslateDTO(word.getWord()), word.getLanguageCode().getCode(), languageCode);
        return createOrGet(translate.getTranslatedText(), languageCode);
    }

}
