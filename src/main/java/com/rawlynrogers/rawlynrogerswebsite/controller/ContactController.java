package com.rawlynrogers.rawlynrogerswebsite.controller;

import com.rawlynrogers.rawlynrogerswebsite.dto.ContactDTO;
import com.rawlynrogers.rawlynrogerswebsite.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ContactDTO getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @PostMapping
    public ContactDTO createContact(@RequestBody ContactDTO contactDTO) {
        return contactService.createContact(contactDTO);
    }

    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable Long id,
                                    @RequestBody ContactDTO updatedContactDTO) {
        return contactService.updateContact(id, updatedContactDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }
}