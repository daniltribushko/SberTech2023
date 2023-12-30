package com.example.sbertech2023.exceptions.users;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 30.12.2023
 *
 * Исключение: Пользователь не является автором обращения
 */
public class UserNotAppealsAuthor extends GlobalAppException {
    public UserNotAppealsAuthor(String userName) {
        super(409, "User " + userName + " is not appeal's author");
    }
}
