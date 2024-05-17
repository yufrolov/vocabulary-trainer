package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.dto.VocabularyDTO;
import com.yufrolov.vocabularytrainer.entity.UserWord;
import com.yufrolov.vocabularytrainer.repository.UserWordRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class VocabularyService {

    UserWordRepository userWordRepository;

    LanguageService languageService;

    ProfileService profileService;

    public VocabularyService(UserWordRepository userWordRepository, LanguageService languageService, ProfileService profileService) {
        this.userWordRepository = userWordRepository;
        this.languageService = languageService;
        this.profileService = profileService;
    }

    public VocabularyDTO getVocabulary(UUID profileId, String languageCode, String languageTranslateCode) {
        languageService.search(languageCode);
        languageService.search(languageTranslateCode);

        profileService.search(profileId);

        var userWord = userWordRepository.findUserWordByProfileId(profileId);
        var translations = userWord.stream()
                .filter(it ->
                        (
                                it.getTranslation().getWord().getLanguageCode().getCode().equals(languageCode)
                                        && it.getTranslation().getTranslateWord().getLanguageCode().getCode().equals(languageTranslateCode)
                        ))
                .map(UserWord::getTranslation)
                .toList();

        HashMap<String, String> words = new HashMap<>();
        for (var translate : translations) {
            words.put(translate.getWord().getWord(), translate.getTranslateWord().getWord());
        }
        return new VocabularyDTO(words);
    }
}
