package com.yufrolov.vocabularytrainer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateDTO {

    @Length(min = 1)
    @JsonAlias({"Text","text"})
    private String translatedText;
}
