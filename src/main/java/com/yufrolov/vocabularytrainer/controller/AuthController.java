package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.JwtRequestDTO;
import com.yufrolov.vocabularytrainer.dto.JwtResponseDTO;
import com.yufrolov.vocabularytrainer.dto.ProfileDTO;
import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @Operation(summary = "User authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user is authenticated",
                    content = @Content)
            , @ApiResponse(responseCode = "400", description = "Bad credentials",
            content = @Content),
            @ApiResponse(responseCode = "404", description = "The user was not found",
                    content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @PostMapping("/auth")
    public JwtResponseDTO createAuthToken(@RequestBody @Valid JwtRequestDTO authRequest) {
        return authService.createAuthToken(authRequest);
    }


//    @Operation(summary = "Creating a user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user account has been created",
                    content = @Content)
            , @ApiResponse(responseCode = "400", description = "Incorrectly entered data",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    public Profile create(@RequestBody @Valid ProfileDTO profileDTO) {
        return authService.registration(profileDTO);
    }
}
