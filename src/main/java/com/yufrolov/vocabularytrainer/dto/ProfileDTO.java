package com.yufrolov.vocabularytrainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProfileDTO {

    @Length(min = 8, max = 40)
    private final String password;

    @Length(min = 2, max = 40)
    private final String surname;

    @Length(min = 1, max = 40)
    private final String name;

    @Length(max = 40)
    private String midname;

    @Length(min = 9, max = 40)
    private final String email;
}
