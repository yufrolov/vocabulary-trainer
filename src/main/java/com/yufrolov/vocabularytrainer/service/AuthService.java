package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.dto.JwtRequestDTO;
import com.yufrolov.vocabularytrainer.dto.JwtResponseDTO;
import com.yufrolov.vocabularytrainer.dto.ProfileDTO;
import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.exception.MyBadCredentialsException;
import com.yufrolov.vocabularytrainer.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ProfileService profileService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthService(ProfileService profileService, JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager) {
        this.profileService = profileService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    public JwtResponseDTO createAuthToken(JwtRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new MyBadCredentialsException("Подпись не совпадает");
        }
        var profile = profileService.getProfile(authRequest.getEmail());
        String token = jwtTokenUtils.generateToken(profile);
        return new JwtResponseDTO(token);
    }

    public Profile registration(ProfileDTO profileDTO) {
        return profileService.create(profileDTO);
    }
}
