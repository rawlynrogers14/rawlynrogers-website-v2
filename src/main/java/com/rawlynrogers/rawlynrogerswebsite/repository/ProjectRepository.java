package com.rawlynrogers.rawlynrogerswebsite.repository;

import com.rawlynrogers.rawlynrogerswebsite.entity.Project;

//gives CRUD Operation, pagination, sorting, and query generation
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}