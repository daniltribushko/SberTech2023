package com.example.sbertech2023.repositories;

import com.example.sbertech2023.models.entities.ViolationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViolationTypeRepository extends JpaRepository<ViolationType, Integer> {
    Optional<ViolationType> findByName(String name);
}
