package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.ProfileDTO;
import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Operation(summary = "Creating a user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user account has been created",
                    content = @Content)
            , @ApiResponse(responseCode = "400", description = "Incorrectly entered data",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Profile create(@RequestBody @Valid ProfileDTO profileDTO) {
        return profileService.create(profileDTO);

    }

    @Operation(summary = "View all user accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profiles found",
                    content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @GetMapping
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }


    @Operation(summary = "Viewing an account by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile found",
                    content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable(name = "id") UUID id) {
        return profileService.getProfile(id);
    }

    @Operation(summary = "Changing user account information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user account has been changed",
                    content = @Content)
            , @ApiResponse(responseCode = "400", description = "Incorrectly entered data",
            content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public UUID update(@PathVariable(name = "id") UUID id, @RequestBody @Valid ProfileDTO profileDTO) {
        return profileService.update(id, profileDTO);
    }

    @Operation(summary = "Deleting a user's account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The profile has been deleted",
                    content = @Content)
            , @ApiResponse(responseCode = "404", description = "The user was not found",
            content = @Content)
            , @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") UUID id) {
        profileService.delete(id);
    }
}
