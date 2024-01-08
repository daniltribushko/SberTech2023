package com.example.sbertech2023.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * @author Tribushko Danil
 * @since 08.01.2024
 *
 * Сервис для работы с типом мероприятия
 */
public interface EventTypeService {
    void saveEventType(@NotBlank String name);
    void deleteEventType(@Min(value = 1, message = "Id can not be less than 1") Integer id);
}
