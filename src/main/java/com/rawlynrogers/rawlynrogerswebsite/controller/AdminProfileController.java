package com.rawlynrogers.rawlynrogerswebsite.controller;

import com.rawlynrogers.rawlynrogerswebsite.dto.ProfileDTO;
import com.rawlynrogers.rawlynrogerswebsite.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/profiles")
public class AdminProfileController {

    private final ProfileService profileService;

    public AdminProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileService.createProfile(profileDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(
            @PathVariable Long id,
            @RequestBody ProfileDTO profileDTO) {

        return ResponseEntity.ok(profileService.updateProfile(id, profileDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}