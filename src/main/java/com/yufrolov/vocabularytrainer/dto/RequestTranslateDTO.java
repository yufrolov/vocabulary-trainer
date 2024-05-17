package com.yufrolov.vocabularytrainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class RequestTranslateDTO {

    @Length(min = 1, max = 100)
    private String word;

    @Length(min = 2, max = 2)
    private String source;

    @Length(min = 2, max = 2)
    private String target;

}
