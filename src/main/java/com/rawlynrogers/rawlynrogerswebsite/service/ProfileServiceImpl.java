package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.ProfileDTO;
import com.rawlynrogers.rawlynrogerswebsite.entity.Contact;
import com.rawlynrogers.rawlynrogerswebsite.entity.Profile;
import com.rawlynrogers.rawlynrogerswebsite.entity.Project;
import com.rawlynrogers.rawlynrogerswebsite.repository.ContactRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.ProfileRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ContactRepository contactRepository;
    private final ProjectRepository projectRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository,
                              ContactRepository contactRepository,
                              ProjectRepository projectRepository) {

        this.profileRepository = profileRepository;
        this.contactRepository = contactRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ProfileDTO getProfileById(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Profile not found with id: " + id));

        return convertToDTO(profile);
    }

    @Override
    public ProfileDTO getActiveProfile() {

        Profile profile = profileRepository.findByActiveTrue()
                .orElseThrow(() ->
                        new RuntimeException("No active profile found"));

        return convertToDTO(profile);
    }

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {

        Profile profile = convertToEntity(profileDTO);

        Profile savedProfile = profileRepository.save(profile);

        return convertToDTO(savedProfile);
    }

    @Override
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {

        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Profile not found with id: " + id));

        existingProfile.setName(profileDTO.getName());
        existingProfile.setActive(profileDTO.isActive());

        if (profileDTO.getContactId() != null) {
            Contact contact = contactRepository.findById(profileDTO.getContactId())
                    .orElseThrow(() -> new RuntimeException(
                            "Contact not found with id: " + profileDTO.getContactId()
                    ));

            existingProfile.setContact(contact);
        } else {
            existingProfile.setContact(null);
        }

        if (profileDTO.getProjectIds() != null) {
            List<Project> projects = projectRepository.findAllById(profileDTO.getProjectIds());
            existingProfile.setProjects(projects);
        } else {
            existingProfile.getProjects().clear();
        }

        Profile savedProfile = profileRepository.save(existingProfile);

        return convertToDTO(savedProfile);
    }

    @Override
    public void deleteProfile(Long id) {

        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Profile not found with id: " + id));

        profileRepository.delete(existingProfile);
    }

    private ProfileDTO convertToDTO(Profile profile) {

        List<Long> projectIds = profile.getProjects()
                .stream()
                .map(Project::getId)
                .toList();

        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.isActive(),
                profile.getContact() != null ? profile.getContact().getId() : null,
                projectIds
        );
    }

    private Profile convertToEntity(ProfileDTO profileDTO) {

        Profile profile = new Profile();

        profile.setName(profileDTO.getName());
        profile.setActive(profileDTO.isActive());

        if (profileDTO.getContactId() != null) {
            Contact contact = contactRepository.findById(profileDTO.getContactId())
                    .orElseThrow(() -> new RuntimeException(
                            "Contact not found with id: " + profileDTO.getContactId()
                    ));

            profile.setContact(contact);
        }

        if (profileDTO.getProjectIds() != null) {
            List<Project> projects = projectRepository.findAllById(profileDTO.getProjectIds());
            profile.setProjects(projects);
        }

        return profile;
    }
}
