package com.example.sbertech2023.models.enums;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 *
 * Роли пользователей
 */
public enum Role {
    USER,
    ORGANIZATION,
    ADMIN;

    public static Optional<Role> mapFromString(String name){
        Role role = null;
        switch (name){
            case "USER" -> role = USER;
            case "ORGANIZATION" -> role = ORGANIZATION;
            case "ADMIN" -> role = ADMIN;
        }

        return Optional.ofNullable(role);
    }

    /**
     * Получение роли из строки без администратора
     *
     * @param name строковое название роли
     * @return роль в типе Optional
     */
    public static Optional<Role> mapFromStringWithoutAdmin(String name){
        Role role = null;
        switch (name){
            case "USER" -> role = USER;
            case "ORGANIZATION" -> role = ORGANIZATION;
        }

        return Optional.ofNullable(role);
    }
}
