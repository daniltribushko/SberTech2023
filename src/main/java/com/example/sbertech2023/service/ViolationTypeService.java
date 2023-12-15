package com.example.sbertech2023.service;

import com.example.sbertech2023.models.entities.ViolationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 * <p>
 * Сервис для работы с типами нарушений
 */
@Validated
public interface ViolationTypeService {
    /**
     * Сохранение типа нарушений
     *
     * @param name название нарушения
     */
    void saveViolationType(@NotBlank String name);

    /**
     * Удаление типа нарушений
     *
     * @param id идентификатор типа нарушения
     */
    void deleteViolationType(@Min(value = 1, message = "Id can not be less than 1") Integer id);

    /**
     * Получение типа нарушения по id
     *
     * @param id идентификатор типа нарушения
     * @return тип нарушения
     */
    ViolationType findViolationTypeById(@Min(value = 1,
            message = "Id can not be less than 1") Integer id);

    /**
     * Получение типа нарушения по навзанию
     *
     * @param name название типа нарушения
     * @return тип нарушения
     */
    ViolationType findViolationTypeByName(@NotBlank String name);
}
