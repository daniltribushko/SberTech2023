package com.example.sbertech2023.repositories;

import com.example.sbertech2023.models.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    Optional<District> findByName(String name);
}
