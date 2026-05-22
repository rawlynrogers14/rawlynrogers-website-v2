package com.rawlynrogers.rawlynrogerswebsite.repository;

import com.rawlynrogers.rawlynrogerswebsite.entity.Media;

//gives CRUD Operation, pagination, sorting, and query generation
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}