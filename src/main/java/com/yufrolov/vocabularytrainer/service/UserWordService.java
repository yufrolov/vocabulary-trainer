package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.entity.Translation;
import com.yufrolov.vocabularytrainer.entity.UserWord;
import com.yufrolov.vocabularytrainer.repository.UserWordRepository;
import org.springframework.stereotype.Service;

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
}
