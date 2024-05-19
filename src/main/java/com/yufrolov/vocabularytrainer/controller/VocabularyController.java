package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.TranslateDTO;
import com.yufrolov.vocabularytrainer.dto.VocabularyDTO;
import com.yufrolov.vocabularytrainer.entity.Vocabulary;
import com.yufrolov.vocabularytrainer.service.VocabularyService;
import com.yufrolov.vocabularytrainer.service.WordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/vocabularies")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    private final WordService wordService;

    public VocabularyController(VocabularyService vocabularyService, WordService wordService) {
        this.vocabularyService = vocabularyService;
        this.wordService = wordService;
    }

    @PostMapping("/{profileId}")
    public Vocabulary translate(@PathVariable(name = "profileId") UUID profileId
            , @Valid @RequestBody VocabularyDTO vocabularyDTO) {
        return vocabularyService.create(profileId, vocabularyDTO);
    }

    @GetMapping("/{id}/{profileId}")
    public Vocabulary getVocabulary(@PathVariable(name = "profileId") UUID profileId,
                                    @PathVariable(name = "id") Long id){
        return vocabularyService.getVocabulary(profileId, id);
    }

    @DeleteMapping("/{id}/{profileId}")
    public Long deleteVocabulary(@PathVariable(name = "profileId") UUID profileId,
                             @PathVariable(name = "id") Long id){
        return vocabularyService.deleteVocabulary(profileId, id);
    }

    @GetMapping("/{profileId}")
    public List<Vocabulary> getAllVocabulary(@PathVariable(name = "profileId") UUID profileId){
        return vocabularyService.getAllVocabularies(profileId);
    }

    @PostMapping("/{id}/words/{profileId}")
    public TranslateDTO translate(@PathVariable(name = "profileId") UUID profileId
            , @PathVariable(name = "id") Long id
            , @Valid @RequestBody TranslateDTO translateDTO) {
        return wordService.translate(profileId, translateDTO, id);
    }

    @DeleteMapping ("/{id}/translations/{translationId}/{profileId}")
    public void deleteTranslationInVocabulary(@PathVariable(name = "profileId") UUID profileId
            , @PathVariable(name = "id") Long id
            , @PathVariable(name = "translationId") Long translationId) {
        vocabularyService.deleteTranslationInVocabulary(profileId, id, translationId);
    }
}
