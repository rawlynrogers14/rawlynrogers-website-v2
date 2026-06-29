package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.ContactDTO;
import com.rawlynrogers.rawlynrogerswebsite.entity.Contact;
import com.rawlynrogers.rawlynrogerswebsite.entity.Media;
import com.rawlynrogers.rawlynrogerswebsite.entity.Profile;
import com.rawlynrogers.rawlynrogerswebsite.entity.Project;
import com.rawlynrogers.rawlynrogerswebsite.repository.ProfileRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.ProjectRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.ContactRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final MediaRepository mediaRepository;
    private final ProfileRepository profileRepository;
    private final ProjectRepository projectRepository;

    public ContactServiceImpl(ContactRepository contactRepository,
                              MediaRepository mediaRepository,
                              ProfileRepository profileRepository,
                              ProjectRepository projectRepository) {
        this.contactRepository = contactRepository;
        this.mediaRepository = mediaRepository;
        this.profileRepository = profileRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ContactDTO getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));

        return convertToDTO(contact);
    }

    @Override
    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = convertToEntity(contactDTO);
        Contact savedContact = contactRepository.save(contact);

        return convertToDTO(savedContact);
    }

    @Override
    public ContactDTO updateContact(Long id, ContactDTO updatedContactDTO) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));

        existingContact.setFirstName(updatedContactDTO.getFirstName());
        existingContact.setMiddleName(updatedContactDTO.getMiddleName());
        existingContact.setLastName(updatedContactDTO.getLastName());
        existingContact.setEmail(updatedContactDTO.getEmail());
        existingContact.setGithubLink(updatedContactDTO.getGithubLink());
        existingContact.setLinkedinLink(updatedContactDTO.getLinkedinLink());
        existingContact.setAboutMe(updatedContactDTO.getAboutMe());

        existingContact.setProfileImage(getMediaOrNull(updatedContactDTO.getProfileImageId()));
        existingContact.setResumePdf(getMediaOrNull(updatedContactDTO.getResumePdfId()));
        existingContact.setCvPdf(getMediaOrNull(updatedContactDTO.getCvPdfId()));

        if (updatedContactDTO.getSlideshowIds() != null) {
            List<Media> slideshow = mediaRepository.findAllById(updatedContactDTO.getSlideshowIds());
            existingContact.setSlideshow(slideshow);
        } else {
            existingContact.getSlideshow().clear();
        }

        Contact savedContact = contactRepository.save(existingContact);

        return convertToDTO(savedContact);
    }

    @Override
    public void deleteContact(Long id) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));

        List<Profile> profiles = profileRepository.findAll();

        for (Profile profile : profiles) {
            if (profile.getContact() != null &&
                    profile.getContact().getId().equals(id)) {
                profile.setContact(null);
            }
        }

        profileRepository.saveAll(profiles);

        List<Project> projects = projectRepository.findAll();

        for (Project project : projects) {
            project.getContributors().remove(existingContact);
        }

        projectRepository.saveAll(projects);

        contactRepository.delete(existingContact);
    }

    private ContactDTO convertToDTO(Contact contact) {
        List<Long> slideshowIds = contact.getSlideshow()
                .stream()
                .map(Media::getId)
                .toList();

        return new ContactDTO(
                contact.getId(),
                contact.getFirstName(),
                contact.getMiddleName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getGithubLink(),
                contact.getLinkedinLink(),
                contact.getProfileImage() != null ? contact.getProfileImage().getId() : null,
                contact.getResumePdf() != null ? contact.getResumePdf().getId() : null,
                contact.getCvPdf() != null ? contact.getCvPdf().getId() : null,
                contact.getAboutMe(),
                slideshowIds
        );
    }

    private Contact convertToEntity(ContactDTO contactDTO) {
        Contact contact = new Contact();

        contact.setFirstName(contactDTO.getFirstName());
        contact.setMiddleName(contactDTO.getMiddleName());
        contact.setLastName(contactDTO.getLastName());
        contact.setEmail(contactDTO.getEmail());
        contact.setGithubLink(contactDTO.getGithubLink());
        contact.setLinkedinLink(contactDTO.getLinkedinLink());
        contact.setAboutMe(contactDTO.getAboutMe());

        contact.setProfileImage(getMediaOrNull(contactDTO.getProfileImageId()));
        contact.setResumePdf(getMediaOrNull(contactDTO.getResumePdfId()));
        contact.setCvPdf(getMediaOrNull(contactDTO.getCvPdfId()));

        if (contactDTO.getSlideshowIds() != null) {
            List<Media> slideshow = mediaRepository.findAllById(contactDTO.getSlideshowIds());
            contact.setSlideshow(slideshow);
        }

        return contact;
    }

    private Media getMediaOrNull(Long mediaId) {
        if (mediaId == null) {
            return null;
        }

        return mediaRepository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + mediaId));
    }
}