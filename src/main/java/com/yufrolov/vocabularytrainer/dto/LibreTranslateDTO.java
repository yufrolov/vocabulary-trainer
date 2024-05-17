package com.yufrolov.vocabularytrainer.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@RequiredArgsConstructor
public class LibreTranslateDTO {

    @NonNull
    @Length(min = 1)
    private final String q;

    @NonNull
    @Length(min = 2, max = 2)
    private final String source;

    @NonNull
    @Length(min = 2, max = 2)
    private final String target;

    @NonNull
    private final String format;

    @NonNull
    private final String api_key;
}
