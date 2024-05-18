package com.yufrolov.vocabularytrainer.repository;

import com.yufrolov.vocabularytrainer.entity.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserWordRepository extends JpaRepository<UserWord, Long> {
    @Query(value = "select * from users_words uw where uw.profile_id = ?1 and uw.translation_id = ?2"
            , nativeQuery = true)
    Optional<UserWord> findUserWordByProfileIdAndTranslationId(UUID profileId, Long translationId);

    @Query(value = "select * from users_words uw where uw.profile_id = ?1"
            , nativeQuery = true)
    ArrayList<UserWord> findUserWordByProfileId(UUID profileId);

}
