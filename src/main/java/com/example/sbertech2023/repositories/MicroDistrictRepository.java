package com.example.sbertech2023.repositories;

import com.example.sbertech2023.models.entities.MicroDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MicroDistrictRepository extends JpaRepository<MicroDistrict, Integer> {
    Optional<MicroDistrict> findByName(String name);
}
