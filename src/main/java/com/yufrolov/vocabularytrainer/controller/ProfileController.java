package com.yufrolov.vocabularytrainer.controller;

import com.yufrolov.vocabularytrainer.dto.ProfileDTO;
import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.service.ProfileService;
import jakarta.validation.Valid;
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

    @PostMapping()
    public Profile create(@RequestBody @Valid ProfileDTO profileDTO) {
        return profileService.create(profileDTO);

    }

    @GetMapping
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable(name = "id") UUID id) {
        return profileService.getProfile(id);
    }

    @PutMapping("/{id}")
    public UUID update(@PathVariable(name = "id") UUID id, @RequestBody @Valid ProfileDTO profileDTO) {
        return profileService.update(id, profileDTO);
    }

    @DeleteMapping("/{id}")
    public UUID delete(@PathVariable(name = "id") UUID id) {
        return profileService.delete(id);
    }
}
