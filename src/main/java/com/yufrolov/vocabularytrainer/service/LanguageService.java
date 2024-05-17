package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.entity.Language;
import com.yufrolov.vocabularytrainer.exception.LanguageNotFoundException;
import com.yufrolov.vocabularytrainer.repository.LanguageRepository;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language search(String code) {
        return languageRepository.findById(code).orElseThrow(
                () -> new LanguageNotFoundException("Допустимые коды языков: " +
                        languageRepository.findAll().stream().map(Language::getCode).toList()
                )
        );
    }
}
