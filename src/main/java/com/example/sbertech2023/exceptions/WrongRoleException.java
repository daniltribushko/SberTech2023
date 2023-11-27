package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 *
 * Исключение: неверная роль
 */
public class WrongRoleException extends GlobalAppException{
    public WrongRoleException(String name) {
        super(400, "Wrong role " + name);
    }
}
