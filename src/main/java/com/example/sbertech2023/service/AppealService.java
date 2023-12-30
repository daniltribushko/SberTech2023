package com.example.sbertech2023.service;

import com.example.sbertech2023.models.dto.request.SaveAppealRequestDto;
import com.example.sbertech2023.models.entities.Appeal;
import com.example.sbertech2023.models.entities.User;
import com.example.sbertech2023.models.enums.AppealStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.List;

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

    /**
     * Принятие обращения
     *
     * @param id идентификатор обращения
     * @param userName имя пользователя
     */
    void acceptAppeal(@Min(value = 1, message = "Id can not be less than 1") Long id, String userName);

    /**
     * Отклонение обращения
     *
     * @param id идентификатор обращения
     * @param userName имя пользователя
     */
    void rejectedAppeal(@Min(value = 1, message = "Id can not be less than 1") Long id, String userName);

    /**
     * Поиск всех обращение по их статусу
     *
     * @param appealStatus статус обращения
     * @return список обращений
     */
    List<Appeal> findAppealsByStatus(AppealStatus appealStatus);

    /**
     * Поиск обращение пользователя с пагинацией
     *
     * @param user пользователь
     * @param pageable страница для пагинации
     * @return
     */
    Page<Appeal> findAppealsByAuthorAndPage(User user, Pageable pageable);

    /**
     * Поиск обращение  с пагинацией
     *
     * @param pageable страница для пагинации
     * @return список обращений с пагинацией
     */
    Page<Appeal> findAppealsByPage(Pageable pageable);

    /**
     * Удаление обращения
     * @param id идентификатор обращения
     * @param userName имя пользователя
     */
    void deleteAppeal(@Min(value = 1, message = "Id can not be less than 1") Long id, String userName) throws IOException;
}
