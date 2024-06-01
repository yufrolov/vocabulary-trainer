package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.TranslateDTO;
import com.yufrolov.vocabularytrainer.dto.VocabularyDTO;
import com.yufrolov.vocabularytrainer.entity.Vocabulary;
import com.yufrolov.vocabularytrainer.service.VocabularyService;
import com.yufrolov.vocabularytrainer.service.WordService;
import com.yufrolov.vocabularytrainer.utils.JwtTokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vocabularies")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    private final WordService wordService;

    private final JwtTokenUtils jwtTokenUtils;

    public VocabularyController(VocabularyService vocabularyService, WordService wordService, JwtTokenUtils jwtTokenUtils) {
        this.vocabularyService = vocabularyService;
        this.wordService = wordService;
        this.jwtTokenUtils = jwtTokenUtils;
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
    @PostMapping()
    public Vocabulary create(@RequestHeader("Authorization") String header
            , @Valid @RequestBody VocabularyDTO vocabularyDTO) {
        var token = jwtTokenUtils.getTokenFromHeaders(header);
        return vocabularyService.create(jwtTokenUtils.getId(token), vocabularyDTO);
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
    @GetMapping("/{id}")
    public Vocabulary getVocabulary(@RequestHeader("Authorization") String header
            , @PathVariable(name = "id") Long id) {
        var token = jwtTokenUtils.getTokenFromHeaders(header);
        return vocabularyService.getVocabulary(jwtTokenUtils.getId(token), id);
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
    @DeleteMapping("/{id}")
    public void deleteVocabulary(@RequestHeader("Authorization") String header
            , @PathVariable(name = "id") Long id) {
        var token = jwtTokenUtils.getTokenFromHeaders(header);
        vocabularyService.deleteVocabulary(jwtTokenUtils.getId(token), id);
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
    @GetMapping()
    public List<Vocabulary> getAllVocabulary(@RequestHeader("Authorization") String header) {
        var token = jwtTokenUtils.getTokenFromHeaders(header);
        return vocabularyService.getAllVocabularies(jwtTokenUtils.getId(token));
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
    @PostMapping("/{id}/words")
    public TranslateDTO translate(@RequestHeader("Authorization") String header
            , @PathVariable(name = "id") Long id
            , @Valid @RequestBody TranslateDTO translateDTO) {
        var token = jwtTokenUtils.getTokenFromHeaders(header);
        return wordService.translate(jwtTokenUtils.getId(token), translateDTO, id);
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
    @DeleteMapping("/{id}/translations/{translationId}")
    public void deleteTranslationInVocabulary(@RequestHeader("Authorization") String header
            , @PathVariable(name = "id") Long id
            , @PathVariable(name = "translationId") Long translationId) {
        var token = jwtTokenUtils.getTokenFromHeaders(header);
        vocabularyService.deleteTranslationInVocabulary(jwtTokenUtils.getId(token), id, translationId);
    }
}
