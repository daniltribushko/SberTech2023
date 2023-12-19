package com.example.sbertech2023.exceptions.users;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 *
 * Исключение: Пользователь уже сохранен
 */
public class UserAlreadyExistException extends GlobalAppException {
    public UserAlreadyExistException(String userName) {
        super(409, "User " + userName + " already exist");
    }
}
