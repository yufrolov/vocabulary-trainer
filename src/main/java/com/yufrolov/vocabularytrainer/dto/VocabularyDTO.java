package com.yufrolov.vocabularytrainer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyDTO {

    @NotBlank
    @Length(min = 2, max = 2)
    private String source;

    @NotBlank
    @Length(min = 2, max = 2)
    private String target;
}
