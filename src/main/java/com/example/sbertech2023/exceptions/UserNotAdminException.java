package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 17.12.2023
 *
 * Исключение: Пользователь не является администратором
 */
public class UserNotAdminException extends GlobalAppException{
    public UserNotAdminException(String userName) {
        super(409, "User is not admin");
    }
}
