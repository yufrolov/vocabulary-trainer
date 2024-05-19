package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.TranslateDTO;
import com.yufrolov.vocabularytrainer.dto.VocabularyDTO;
import com.yufrolov.vocabularytrainer.entity.Vocabulary;
import com.yufrolov.vocabularytrainer.service.VocabularyService;
import com.yufrolov.vocabularytrainer.service.WordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Creating a vocabulary for a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The vocabulary has been created",
                    content = @Content)
            , @ApiResponse(responseCode = "400", description = "Incorrectly entered data",
            content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{profileId}")
    public Vocabulary create(@PathVariable(name = "profileId") UUID profileId
            , @Valid @RequestBody VocabularyDTO vocabularyDTO) {
        return vocabularyService.create(profileId, vocabularyDTO);
    }

    @Operation(summary = "Viewing a vocabulary by a specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vocabulary found",
                    content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user or vocabulary was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @GetMapping("/{id}/{profileId}")
    public Vocabulary getVocabulary(@PathVariable(name = "profileId") UUID profileId,
                                    @PathVariable(name = "id") Long id) {
        return vocabularyService.getVocabulary(profileId, id);
    }

    @Operation(summary = "Deleting a vocabulary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The vocabulary has been deleted",
                    content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user or vocabulary was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/{profileId}")
    public void deleteVocabulary(@PathVariable(name = "profileId") UUID profileId,
                                 @PathVariable(name = "id") Long id) {
        vocabularyService.deleteVocabulary(profileId, id);
    }

    @Operation(summary = "View all user vocabularies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vocabularies found",
                    content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @GetMapping("/{profileId}")
    public List<Vocabulary> getAllVocabulary(@PathVariable(name = "profileId") UUID profileId) {
        return vocabularyService.getAllVocabularies(profileId);
    }

    @Operation(summary = "Adding a word and its translation to the vocabulary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Adding a word to the vocabulary",
                    content = @Content)
            , @ApiResponse(responseCode = "400", description = "Incorrectly entered data",
            content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user or vocabulary was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/words/{profileId}")
    public TranslateDTO translate(@PathVariable(name = "profileId") UUID profileId
            , @PathVariable(name = "id") Long id
            , @Valid @RequestBody TranslateDTO translateDTO) {
        return wordService.translate(profileId, translateDTO, id);
    }

    @Operation(summary = "Deleting a translation from the vocabulary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The translation has been deleted",
                    content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user or vocabulary was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/translations/{translationId}/{profileId}")
    public void deleteTranslationInVocabulary(@PathVariable(name = "profileId") UUID profileId
            , @PathVariable(name = "id") Long id
            , @PathVariable(name = "translationId") Long translationId) {
        vocabularyService.deleteTranslationInVocabulary(profileId, id, translationId);
    }
}
