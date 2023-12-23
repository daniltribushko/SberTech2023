package com.example.sbertech2023.repositories;

import com.example.sbertech2023.models.entities.Appeal;
import com.example.sbertech2023.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppealPageRepository extends PagingAndSortingRepository<Appeal, Long> {
    Page<Appeal> findAllByAuthor(User user, Pageable pageable);
}
