package com.yufrolov.vocabularytrainer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JwtRequestDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
