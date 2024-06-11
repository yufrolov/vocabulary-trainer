package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.ProfileDTO;
import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.service.ProfileService;
import com.yufrolov.vocabularytrainer.utils.JwtTokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/profiles")
@SecurityRequirement(name = "bearerAuth")
public class ProfileController {

    private final ProfileService profileService;

    private final JwtTokenUtils jwtTokenUtils;

    public ProfileController(ProfileService profileService, JwtTokenUtils jwtTokenUtils) {
        this.profileService = profileService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Operation(summary = "View all user accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profiles found",
                    content = @Content)
            ,@ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content)
            ,@ApiResponse(responseCode = "403", description = "Forbidden",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @GetMapping("/list")
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }


    @Operation(summary = "Viewing an account by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile found",
                    content = @Content)
            ,@ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @GetMapping()
    public Profile getProfile(@Parameter(hidden = true) @RequestHeader("Authorization") String header) {
        var token = jwtTokenUtils.getTokenFromHeaders(header);
        return profileService.getProfile(jwtTokenUtils.getId(token));
    }

    @Operation(summary = "Changing user account information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user account has been changed",
                    content = @Content)
            , @ApiResponse(responseCode = "400", description = "Incorrectly entered data",
            content = @Content)
            ,@ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping()
    public Profile update(@Parameter(hidden = true) @RequestHeader("Authorization") String header, @RequestBody @Valid ProfileDTO profileDTO) {
        var token = jwtTokenUtils.getTokenFromHeaders(header);
        return profileService.update(jwtTokenUtils.getId(token), profileDTO);
    }

    @Operation(summary = "Deleting a user's account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The profile has been deleted",
                    content = @Content)
            ,@ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public void delete(@Parameter(hidden = true) @RequestHeader("Authorization") String header) {
        var token = jwtTokenUtils.getTokenFromHeaders(header);
        profileService.delete(jwtTokenUtils.getId(token));
    }
}
