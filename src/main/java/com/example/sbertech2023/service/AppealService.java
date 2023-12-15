package com.example.sbertech2023.service;

import com.example.sbertech2023.models.dto.request.SaveAppealRequestDto;
import com.example.sbertech2023.models.entities.Appeal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Сервис для работы с обращениями
 */
@Service
@Validated
public interface AppealService {
    /**
     * Сохранение обращения
     *
     * @param request запрос на сохранения обращения
     */
    void saveAppeal(@Valid SaveAppealRequestDto request, String username);

    /**
     * Получение обращения по имени
     *
     * @param title заголовок обращения
     * @return сущность обращения
     */
    Optional<Appeal> findAppealByTitle(@NotNull String title);

    /**
     * Получение обращения по id
     *
     * @param id идентификатор обращения
     * @return сущность обращения
     */
}
