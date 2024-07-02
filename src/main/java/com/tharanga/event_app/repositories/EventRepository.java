package com.tharanga.event_app.repositories;


import com.tharanga.event_app.domain.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{
}
