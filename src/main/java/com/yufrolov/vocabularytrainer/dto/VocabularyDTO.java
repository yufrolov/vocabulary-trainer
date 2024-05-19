package com.yufrolov.vocabularytrainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyDTO {

    @Length(min = 2, max = 2)
    private String source;

    @Length(min = 2, max = 2)
    private String target;
}
