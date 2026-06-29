package com.rawlynrogers.rawlynrogerswebsite.controller;

import com.rawlynrogers.rawlynrogerswebsite.dto.ProfileDTO;
import com.rawlynrogers.rawlynrogerswebsite.service.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<ProfileDTO> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    public ProfileDTO getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }

    @GetMapping("/active")
    public ProfileDTO getActiveProfile() {
        return profileService.getActiveProfile();
    }
}