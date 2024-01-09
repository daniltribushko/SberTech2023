package com.example.sbertech2023.exceptions.users;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 08.01.2024
 *
 * Исключение: Пользователь не является организацией
 */
public class UserNotOrganizationException extends GlobalAppException {
    public UserNotOrganizationException(String userName) {
        super(409, "User " + userName + " is not organization");
    }
}
