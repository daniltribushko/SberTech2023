package com.example.sbertech2023.exceptions.users;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 19.12.2023
 *
 * Исключение: Пользователь с указанным идентификатором не найден
 */
public class UserByIdNotFoundException extends GlobalAppException {
    public UserByIdNotFoundException(Long id) {
        super(404, "User with " + id + " id not found");
    }
}
