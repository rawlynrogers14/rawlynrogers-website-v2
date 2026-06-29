package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {

    List<ProfileDTO> getAllProfiles();

    ProfileDTO getProfileById(Long id);

    ProfileDTO getActiveProfile();

    ProfileDTO createProfile(ProfileDTO profileDTO);

    ProfileDTO updateProfile(Long id, ProfileDTO profileDTO);

    ProfileDTO activateProfile(Long id);

    void deleteProfile(Long id);
}