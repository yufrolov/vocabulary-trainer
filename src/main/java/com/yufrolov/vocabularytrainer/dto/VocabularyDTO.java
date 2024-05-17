package com.yufrolov.vocabularytrainer.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Data
@RequiredArgsConstructor
public class VocabularyDTO {

    @NonNull
    private final HashMap<String, String> vocabulary;
}
