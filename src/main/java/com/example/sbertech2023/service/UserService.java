package com.example.sbertech2023.service;

import com.example.sbertech2023.models.entities.User;

/**
 * @author Tribushko Danil
 * @since 19.12.2023
 *
 * Сервис для работы с пользователями
 */
public interface UserService {
    /**
     * Поиск пользователя по id
     *
     * @param id идентификатор пользователя
     * @return сущность пользователя
     */
    User findById(Long id);

    /**
     * Поиск пользователя по логину пользователю
     *
     * @param userName логин пользователя
     * @return сущность пользователя
     */
    User findByUserName(String userName);

    /**
     * Проверка является ли пользователь администратором
     *
     * @param user сущность пользователя
     * @return является ли пользователь администратором
     */
    boolean isAdmin(User user);

    /**
     * Проверка является ли пользователь организацией
     *
     * @param user сущность пользователя
     * @return является ли пользователь организацией
     */
    boolean isOrganization(User user);
}
