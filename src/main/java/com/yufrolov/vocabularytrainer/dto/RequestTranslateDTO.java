package com.yufrolov.vocabularytrainer.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@RequiredArgsConstructor
public class RequestTranslateDTO {

    @NonNull
    @Length(min = 1, max = 100)
    private final String word;

    @NonNull
    @Length(min = 2, max = 2)
    private final String source;

    @NonNull
    @Length(min = 2, max = 2)
    private final String target;

}
