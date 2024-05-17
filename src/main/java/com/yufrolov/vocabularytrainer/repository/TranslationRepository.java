package com.yufrolov.vocabularytrainer.repository;

import com.yufrolov.vocabularytrainer.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
    @Query(value = "select * from translations t where t.word_id = ?1 and t.translate_word_id = ?2"
            , nativeQuery = true)
    Optional<Translation> findTranslationByWordIdAndTranslateWordId(Long wordId, Long translateWordId);
}
