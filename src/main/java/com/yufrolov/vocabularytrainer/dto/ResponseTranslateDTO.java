package com.yufrolov.vocabularytrainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTranslateDTO {

    @Length(min = 1)
    private String translatedText;
}
