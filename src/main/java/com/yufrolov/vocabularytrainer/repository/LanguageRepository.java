package com.yufrolov.vocabularytrainer.repository;

import com.yufrolov.vocabularytrainer.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {

}
