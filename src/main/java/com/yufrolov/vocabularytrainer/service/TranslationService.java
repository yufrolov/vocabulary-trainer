package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.entity.Translation;
import com.yufrolov.vocabularytrainer.entity.Word;
import com.yufrolov.vocabularytrainer.repository.TranslationRepository;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {
    private final TranslationRepository translationRepository;

    public TranslationService(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    public Translation createOrGet(Word word, Word translateWord) {
        var translation = translationRepository
                .findTranslationByWordIdAndTranslateWordId(word.getId(), translateWord.getId())
                .orElse(null);
        if (translation == null) {
            return translationRepository.save(new Translation(word, translateWord));
        }
        return translation;
    }
}
