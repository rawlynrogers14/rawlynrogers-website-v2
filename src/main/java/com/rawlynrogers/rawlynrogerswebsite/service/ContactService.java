package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.ContactDTO;

import java.util.List;

public interface ContactService {

    List<ContactDTO> getAllContacts();

    ContactDTO getContactById(Long id);

    ContactDTO createContact(ContactDTO contactDTO);

    ContactDTO updateContact(Long id, ContactDTO contactDTO);

    void deleteContact(Long id);
}