package com.example.sbertech2023.repositories;

import com.example.sbertech2023.models.entities.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppealRepository extends JpaRepository<Appeal, Long> {
    Optional<Appeal> findByTitle(String title);
}
