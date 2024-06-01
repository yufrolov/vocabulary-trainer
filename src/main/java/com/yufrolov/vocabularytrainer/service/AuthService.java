package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.dto.JwtRequestDTO;
import com.yufrolov.vocabularytrainer.dto.JwtResponseDTO;
import com.yufrolov.vocabularytrainer.dto.ProfileDTO;
import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.utils.JwtTokenUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ProfileService profileService;
    private final JwtTokenUtils jwtTokenUtils;

    public AuthService(ProfileService profileService, JwtTokenUtils jwtTokenUtils) {
        this.profileService = profileService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    public JwtResponseDTO createAuthToken(JwtRequestDTO authRequest) {

        var profile = profileService.getProfile(authRequest.getEmail(), authRequest.getPassword());
        String token = jwtTokenUtils.generateToken(profile);
        return new JwtResponseDTO(token);
    }

    public Profile registration(ProfileDTO profileDTO) {
        return profileService.create(profileDTO);
    }
}
