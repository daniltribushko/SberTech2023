package com.example.sbertech2023.repositories;

import com.example.sbertech2023.models.entities.Appeal;
import com.example.sbertech2023.models.enums.AppealStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppealRepository extends JpaRepository<Appeal, Long> {
    Optional<Appeal> findByTitle(String title);
    List<Appeal> findAllByStatus(AppealStatus status);

    @Modifying
    @Query(value = "delete from appeals WHERE id=?1", nativeQuery = true)
    void deleteById(Long id);
}
