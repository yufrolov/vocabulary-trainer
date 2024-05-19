package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.entity.Translation;
import com.yufrolov.vocabularytrainer.entity.Vocabulary;
import com.yufrolov.vocabularytrainer.entity.VocabularyTranslation;
import com.yufrolov.vocabularytrainer.repository.VocabularyTranslationRepository;
import org.springframework.stereotype.Service;

@Service
public class VocabularyTranslationService {

    private final VocabularyTranslationRepository vocabularyTranslationRepository;

    public VocabularyTranslationService(VocabularyTranslationRepository vocabularyTranslationRepository) {
        this.vocabularyTranslationRepository = vocabularyTranslationRepository;
    }

    public void create(Vocabulary vocabulary, Translation translation) {
        var vocabularyTranslation = vocabularyTranslationRepository
                .findVocabularyTranslationByVocabularyIdAndTranslationId(vocabulary.getId(), translation.getId()).orElse(null);
        if (vocabularyTranslation == null) {
            vocabularyTranslationRepository.save(new VocabularyTranslation(vocabulary, translation));
        }
    }

    public void deleteByVocabularyIdAndTranslationId(Long vocabularyId, Long translationId) {
        vocabularyTranslationRepository
                .findVocabularyTranslationByVocabularyIdAndTranslationId(vocabularyId, translationId).
                ifPresent(vocabularyTranslationRepository::delete);
    }
}
