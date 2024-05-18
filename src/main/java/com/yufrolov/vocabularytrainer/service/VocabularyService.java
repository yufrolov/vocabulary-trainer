package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.dto.VocabularyDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class VocabularyService {

    UserWordService userWordService;

    LanguageService languageService;

    ProfileService profileService;

    public VocabularyService(UserWordService userWordService, LanguageService languageService, ProfileService profileService) {
        this.userWordService = userWordService;
        this.languageService = languageService;
        this.profileService = profileService;
    }

    private void parametersSatisfy(UUID profileId, String languageCode, String languageTranslateCode) {
        languageService.search(languageCode);
        languageService.search(languageTranslateCode);

        profileService.search(profileId);
    }

    public VocabularyDTO getVocabulary(UUID profileId, String languageCode, String languageTranslateCode) {
        parametersSatisfy(profileId, languageCode, languageTranslateCode);
        var translations = userWordService.getUserTranslation(profileId, languageCode, languageTranslateCode);

        HashMap<String, String> words = new HashMap<>();
        for (var translate : translations) {
            words.put(translate.getWord().getWord(), translate.getTranslateWord().getWord());
        }
        return new VocabularyDTO(words);
    }

    public String deleteVocabulary(UUID profileId, String languageCode, String languageTranslateCode) {
        parametersSatisfy(profileId, languageCode, languageTranslateCode);
        return userWordService.deleteUserWords(profileId, languageCode, languageTranslateCode);
    }
}
