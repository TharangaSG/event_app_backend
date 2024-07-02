package com.tharanga.event_app.repositories;

import com.tharanga.event_app.domain.entities.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee,Long> {
}
