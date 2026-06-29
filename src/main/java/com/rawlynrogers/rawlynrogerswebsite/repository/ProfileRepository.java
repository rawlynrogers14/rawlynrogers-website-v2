package com.rawlynrogers.rawlynrogerswebsite.repository;

import com.rawlynrogers.rawlynrogerswebsite.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByActiveTrue();
}