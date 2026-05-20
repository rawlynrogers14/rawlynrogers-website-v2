package com.rawlynrogers.rawlynrogerswebsite.repository;

import com.rawlynrogers.rawlynrogerswebsite.entity.Contact;

//gives CRUD Operation, pagination, sorting, and query generation
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}