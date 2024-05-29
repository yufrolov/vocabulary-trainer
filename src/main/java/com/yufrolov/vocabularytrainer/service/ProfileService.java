package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.dto.ProfileDTO;
import com.yufrolov.vocabularytrainer.entity.Profile;
import com.yufrolov.vocabularytrainer.exception.ProfileAlreadyExistException;
import com.yufrolov.vocabularytrainer.exception.ProfileNotFoundException;
import com.yufrolov.vocabularytrainer.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    private boolean isProfileExist(String email) {
        if (profileRepository.findByEmail(email).isPresent()) {
            throw new ProfileAlreadyExistException("There is a profile with this email");
        }
        return false;
    }

    public Profile getProfile(UUID id) {
        return profileRepository.findById(id).orElseThrow(
                () -> new ProfileNotFoundException("Not found user"));
    }

    private Profile mapToEntity(ProfileDTO profileDTO) {
        return new Profile(
                profileDTO.getPassword()
                , profileDTO.getSurname()
                , profileDTO.getName()
                , profileDTO.getMidname()
                , profileDTO.getEmail()
        );
    }

    public Profile create(ProfileDTO profileDTO) {
        if (!isProfileExist(profileDTO.getEmail())) {
            return profileRepository.save(mapToEntity(profileDTO));
        }
        return null;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public void delete(UUID id) {
        profileRepository.findById(id).orElseThrow(
                () -> new ProfileNotFoundException("Not found user")
        );
        profileRepository.deleteById(id);
    }

    public Profile update(UUID id, ProfileDTO profileDTO) {
        var profile = profileRepository.findById(id).orElseThrow(
                () -> new ProfileNotFoundException("Not found user")
        );
        return profileRepository
                .save(updateFieldProfile(profile, profileDTO));
    }

    private Profile updateFieldProfile(Profile profile, ProfileDTO profileDTO) {
        var updateProfile = new Profile(profile);
        String val;
        val = profileDTO.getSurname();
        if (val != null) {
            updateProfile.setSurname(val);
        }

        val = profileDTO.getName();
        if (val != null) {
            updateProfile.setName(val);
        }

        val = profileDTO.getMidname();
        if (val != null) {
            updateProfile.setMidname(val);
        }

        val = profileDTO.getEmail();
        if (val != null) {
            updateProfile.setEmail(val);
        }
        return updateProfile;
    }
}
