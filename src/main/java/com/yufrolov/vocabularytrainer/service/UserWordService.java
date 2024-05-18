package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.entity.Translation;
import com.yufrolov.vocabularytrainer.entity.UserWord;
import com.yufrolov.vocabularytrainer.repository.UserWordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserWordService {
    private final UserWordRepository userWordRepository;

    public UserWordService(UserWordRepository userWordRepository) {
        this.userWordRepository = userWordRepository;
    }

    public UserWord createOrGet(Profile profile, Translation translation) {
        var userWord = userWordRepository
                .findUserWordByProfileIdAndTranslationId(profile.getId(), translation.getId())
                .orElse(null);
        if (userWord == null) {
            return userWordRepository.save(new UserWord(profile, translation));
        }
        return userWord;
    }

    public List<UserWord> getUserWords(UUID profileId) {
        return userWordRepository.findUserWordByProfileId(profileId);
    }

    public List<Translation> getUserTranslation(UUID profileId, String languageCode, String languageTranslateCode) {
        var userWord = getUserWords(profileId);
        return userWord.stream()
                .filter(it ->
                        (
                                it.getTranslation().getWord().getLanguageCode().getCode().equals(languageCode)
                                        && it.getTranslation().getTranslateWord().getLanguageCode().getCode().equals(languageTranslateCode)
                        ))
                .map(UserWord::getTranslation)
                .toList();
    }

    public List<UserWord> getUserWords(UUID profileId, String languageCode, String languageTranslateCode) {
        var userWord = getUserWords(profileId);
        return userWord.stream()
                .filter(it ->
                        (
                                it.getTranslation().getWord().getLanguageCode().getCode().equals(languageCode)
                                        && it.getTranslation().getTranslateWord().getLanguageCode().getCode().equals(languageTranslateCode)
                        ))
                .toList();
    }

    public String deleteUserWords(UUID profileId, String languageCode, String languageTranslateCode) {
        var userWords = getUserWords(profileId, languageCode, languageTranslateCode);
        for (var userWord : userWords) {
            userWordRepository.deleteById(userWord.getId());
        }
        return languageCode + "-" + languageTranslateCode;
    }
}
