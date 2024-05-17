package com.yufrolov.vocabularytrainer.repository;

import com.yufrolov.vocabularytrainer.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(value = "select * from words w where w.word like ?1 and w.language_code = ?2"
            , nativeQuery = true)
    Optional<Word> findWordByTextAndLanguageCode(String word, String code);

}