package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.ContactDTO;
import com.rawlynrogers.rawlynrogerswebsite.entity.Contact;
import com.rawlynrogers.rawlynrogerswebsite.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
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
        existingContact.setProfileImageUrl(updatedContactDTO.getProfileImageUrl());
        existingContact.setResumePdfUrl(updatedContactDTO.getResumePdfUrl());
        existingContact.setCvPdfUrl(updatedContactDTO.getCvPdfUrl());
        existingContact.setAboutMe(updatedContactDTO.getAboutMe());

        Contact savedContact = contactRepository.save(existingContact);

        return convertToDTO(savedContact);
    }

    @Override
    public void deleteContact(Long id) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));

        contactRepository.delete(existingContact);
    }

    private ContactDTO convertToDTO(Contact contact) {
        return new ContactDTO(
                contact.getId(),
                contact.getFirstName(),
                contact.getMiddleName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getGithubLink(),
                contact.getLinkedinLink(),
                contact.getProfileImageUrl(),
                contact.getResumePdfUrl(),
                contact.getCvPdfUrl(),
                contact.getAboutMe()
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
        contact.setProfileImageUrl(contactDTO.getProfileImageUrl());
        contact.setResumePdfUrl(contactDTO.getResumePdfUrl());
        contact.setCvPdfUrl(contactDTO.getCvPdfUrl());
        contact.setAboutMe(contactDTO.getAboutMe());

        return contact;
    }
}