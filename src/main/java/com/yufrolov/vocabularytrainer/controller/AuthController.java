package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.JwtRequestDTO;
import com.yufrolov.vocabularytrainer.dto.JwtResponseDTO;
import com.yufrolov.vocabularytrainer.dto.ProfileDTO;
import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public JwtResponseDTO createAuthToken(@RequestBody @Valid JwtRequestDTO authRequest){
        return authService.createAuthToken(authRequest);
    }


    @PostMapping("/registration")
    public Profile create(@RequestBody @Valid ProfileDTO profileDTO) {
        return authService.registration(profileDTO);
    }
}
