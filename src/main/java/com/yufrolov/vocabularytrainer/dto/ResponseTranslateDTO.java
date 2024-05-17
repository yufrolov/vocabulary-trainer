package com.yufrolov.vocabularytrainer.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@RequiredArgsConstructor
public class ResponseTranslateDTO {

    @NonNull
    @Length(min = 1)
    private final String translatedText;
}
