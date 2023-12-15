package com.example.sbertech2023.models.enums;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Статус обращения
 */
public enum AppealStatus {
    /**
     * Ожидает ответа от администратора
     */
    WAITING,

    /**
     * Принято администратором
     */
    ACCEPTED,

    /**
     * Отклонено администратором
     */
    REJECTED;

    /**
     * Получение статуса обращения из строки
     *
     * @param status статус обращения в формате строки
     * @return статус обращение в формате Optional
     */
    public static Optional<AppealStatus> mapFromString(String status){
        AppealStatus result;
        switch (status){
            case "WAITING" -> result = WAITING;
            case "ACCEPTED" -> result = ACCEPTED;
            case "REJECTED" -> result = REJECTED;
            default -> result = null;
        }
        return Optional.ofNullable(result);
    }
}