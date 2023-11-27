package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 *
 * Исключение: Пользователь с именем не найден
 */
public class UserByUserNameNotFoundException extends GlobalAppException{
    public UserByUserNameNotFoundException(String userName) {
        super(404, "User " + userName + " not found");
    }
}
