package com.yufrolov.vocabularytrainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibreTranslateDTO {

    @Length(min = 1)
    private String q;

    @Length(min = 2, max = 2)
    private String source;

    @Length(min = 2, max = 2)
    private String target;

    private String format;

    private String api_key;
}
