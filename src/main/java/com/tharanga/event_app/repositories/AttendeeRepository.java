package com.tharanga.event_app.repositories;

import com.tharanga.event_app.domain.entities.AttendeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendeeRepository extends JpaRepository<AttendeeEntity,Long> {
}
