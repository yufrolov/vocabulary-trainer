package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.RequestTranslateDTO;
import com.yufrolov.vocabularytrainer.dto.ResponseTranslateDTO;
import com.yufrolov.vocabularytrainer.service.WordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/word")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping("/{profileId}")
    public ResponseTranslateDTO translate(@PathVariable(name = "profileId") UUID id
            , @Valid @RequestBody RequestTranslateDTO requestTranslateDTO) {
        return wordService.translate(id, requestTranslateDTO);
    }
}
