package com.yufrolov.vocabularytrainer.repository;

import com.yufrolov.vocabularytrainer.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
    @Query(value = "select * from vocabularies v where v.profile_id = ?1 and v.id = ?2"
            , nativeQuery = true)
    Optional<Vocabulary> findVocabularyByIdAndProfileId(UUID profileId, Long id);

    @Query(value = "select * from vocabularies v where v.profile_id = ?1"
            , nativeQuery = true)
    ArrayList<Vocabulary> findVocabulariesByProfileId(UUID profileId);

}
