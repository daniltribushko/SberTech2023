package com.example.sbertech2023.repositories;

import com.example.sbertech2023.models.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Integer> {
    Optional<EventType> findByName(String name);
}
