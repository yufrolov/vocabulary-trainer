package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.dto.LibreTranslateDTO;
import com.yufrolov.vocabularytrainer.dto.RequestTranslateDTO;
import com.yufrolov.vocabularytrainer.dto.ResponseTranslateDTO;
import com.yufrolov.vocabularytrainer.entity.Translation;
import com.yufrolov.vocabularytrainer.entity.Word;
import com.yufrolov.vocabularytrainer.repository.WordRepository;
import com.yufrolov.vocabularytrainer.service.thirdparty.LibreTranslateClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WordService {
    private final WordRepository wordRepository;

    private final ProfileService profileService;

    private final UserWordService userWordService;

    private final LanguageService languageService;

    private final TranslationService translationService;

    private final LibreTranslateClient libreTranslateClient;

    public WordService(WordRepository wordRepository
            , UserWordService userWordService
            , ProfileService profileService
            , LanguageService languageService
            , TranslationService translationService
            , LibreTranslateClient libreTranslateClient) {
        this.wordRepository = wordRepository;
        this.profileService = profileService;
        this.userWordService = userWordService;
        this.languageService = languageService;
        this.translationService = translationService;
        this.libreTranslateClient = libreTranslateClient;
    }

    private ResponseTranslateDTO translateExternal(RequestTranslateDTO requestTranslateDTO) {
        languageService.search(requestTranslateDTO.getTarget());
        return libreTranslateClient.translate(
                new LibreTranslateDTO(
                        requestTranslateDTO.getWord()
                        , requestTranslateDTO.getSource()
                        , requestTranslateDTO.getTarget()
                        , "text"
                        , ""
                ));
    }

    private Word createOrGet(String text, String languageCode) {
        return wordRepository
                .findWordByTextAndLanguageCode(text, languageCode)
                .orElseGet(() -> wordRepository.save(new Word(text, languageService.search(languageCode))));
    }

    private void savingWordConnections(UUID profileId, Word word, Word translateWord) {
        userWordService.createOrGet(
                profileService.search(profileId)
                , translationService.createOrGet(word, translateWord)
        );
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


    public ResponseTranslateDTO translate(UUID id, RequestTranslateDTO requestTranslateDTO) {
        Word translateWord;
        var word = createOrGet(requestTranslateDTO.getWord(), requestTranslateDTO.getSource());

        translateWord = findTranslateWord(word, requestTranslateDTO.getTarget());

        var response = translateWord == null ? translateExternal(requestTranslateDTO) : new ResponseTranslateDTO(translateWord.getWord());

        translateWord = createOrGet(response.getTranslatedText(), requestTranslateDTO.getTarget());

        savingWordConnections(id, word, translateWord);
        return response;
    }

}
