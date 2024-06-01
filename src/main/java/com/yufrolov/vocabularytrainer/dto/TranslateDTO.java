package com.yufrolov.vocabularytrainer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateDTO {

    @NotBlank
    @Length(min = 1)
    @JsonAlias({"Text", "text"})
    private String translatedText;
}
