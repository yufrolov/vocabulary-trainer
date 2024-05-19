package com.yufrolov.vocabularytrainer.repository;

import com.yufrolov.vocabularytrainer.entity.VocabularyTranslation;
import com.yufrolov.vocabularytrainer.entity.compositepk.VocabularyTranslationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VocabularyTranslationRepository extends JpaRepository<VocabularyTranslation, VocabularyTranslationId> {
    @Query(value = "select * from vocabularies_translations vt where vt.vocabulary_id = ?1 " +
            "and vt.translation_id = ?2"
            , nativeQuery = true)
    Optional<VocabularyTranslation> findVocabularyTranslationByVocabularyIdAndTranslationId(Long vocabularyId, Long translationId);
}
