package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.VocabularyDTO;
import com.yufrolov.vocabularytrainer.service.VocabularyService;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/{lang}-{translateLang}/{profileId}")
    public String deleteVocabulary(@PathVariable(name = "profileId") UUID profileId
            , @PathVariable(name = "lang") String languageCode
            , @PathVariable(name = "translateLang") String languageTranslateCode) {
        return vocabularyService.deleteVocabulary(profileId, languageCode, languageTranslateCode);
    }
}
