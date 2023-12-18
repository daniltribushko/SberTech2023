package com.example.sbertech2023.service;

import com.example.sbertech2023.models.dto.request.SaveAppealRequestDto;
import com.example.sbertech2023.models.entities.Appeal;
import com.example.sbertech2023.models.enums.AppealStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
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
    Appeal findAppealByTitle(@NotNull String title);

    /**
     * Получение обращения по id
     *
     * @param id идентификатор обращения
     * @return сущность обращения
     */
    Appeal findAppealById(@Min(value = 1, message = "Id can not be less than 1") Long id);


    void acceptAppeal(@Min(value = 1, message = "Id can not be less than 1") Long id, String userName);
    void rejectedAppeal(@Min(value = 1, message = "Id can not be less than 1") Long id, String userName);
    List<Appeal> findAppealsByStatus(AppealStatus appealStatus);
}
