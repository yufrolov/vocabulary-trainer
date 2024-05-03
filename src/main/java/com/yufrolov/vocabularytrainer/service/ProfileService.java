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

    private boolean isProfileExist(String login, String email){
        if(profileRepository.findByLoginAndEmail(login,email).isPresent()){
            throw new ProfileAlreadyExistException("There is a profile with this login and email");
        }
        else if (profileRepository.findByLogin(login).isPresent()) {
            throw new ProfileAlreadyExistException("There is a profile with this login");
        }
        else if (profileRepository.findByEmail(email).isPresent()) {
            throw new ProfileAlreadyExistException("There is a profile with this email");
        }
        return false;
    }

    public UUID create(ProfileDTO profileDTO){
        if(!isProfileExist(profileDTO.getLogin(),profileDTO.getEmail())){
            var profile = new Profile(profileDTO.getLogin()
                    ,profileDTO.getPassword()
                    ,profileDTO.getSurname()
                    ,profileDTO.getName()
                    ,profileDTO.getMidname()
                    ,profileDTO.getEmail()
            );
            return profileRepository.save(profile).getId();
        }
        return null;
    }

    public List<Profile> getAllProfiles(){
        return profileRepository.findAll();
    }

    public UUID delete(UUID id){
        var profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            profileRepository.deleteById(id);
            return id;
        }
        throw new ProfileNotFoundException("Not found user");
    }

    public UUID update(UUID id, ProfileDTO profileDTO){
        var profile = profileRepository.findById(id);
        if (profile.isPresent()){
            return profileRepository
                    .save(updateFieldProfile(profile.get(), profileDTO))
                    .getId();
        }
        throw new ProfileNotFoundException("Not found user");
    }

    private Profile updateFieldProfile(Profile profile, ProfileDTO profileDTO){
        var updateProfile = new Profile(profile);
        String val;
        val = profileDTO.getSurname();
        if(val != null){
            updateProfile.setSurname(val);
        }

        val = profileDTO.getName();
        if(val != null){
            updateProfile.setName(val);
        }

        val = profileDTO.getMidname();
        if(val != null){
            updateProfile.setMidname(val);
        }

        val = profileDTO.getEmail();
        if(val != null){
            updateProfile.setEmail(val);
        }
        return updateProfile;
    }
}
