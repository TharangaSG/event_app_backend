package com.tharanga.event_app.auth.repositories;

import com.tharanga.event_app.auth.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String username);
}
