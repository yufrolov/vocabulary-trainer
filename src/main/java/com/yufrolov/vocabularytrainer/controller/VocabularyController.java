package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.VocabularyDTO;
import com.yufrolov.vocabularytrainer.service.VocabularyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/vocabulary")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    public VocabularyController(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @GetMapping("/{lang}-{translateLang}/{profileId}")
    public VocabularyDTO getVocabulary(@PathVariable(name = "profileId") UUID profileId
            , @PathVariable(name = "lang") String languageCode
            , @PathVariable(name = "translateLang") String languageTranslateCode) {
        return vocabularyService.getVocabulary(profileId, languageCode, languageTranslateCode);
    }
}
