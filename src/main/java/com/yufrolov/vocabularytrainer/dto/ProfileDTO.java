package com.yufrolov.vocabularytrainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class ProfileDTO {

    @Length(min = 6, max = 40)
    String login;

    @Length(min = 8, max = 40)
    String password;

    @Length(min = 2, max = 40)
    String surname;

    @Length(min = 1, max = 40)
    String name;

    @Length(max = 40)
    String midname;

    @Length(min = 9, max = 40)
    String email;
}
