package com.example.sbertech2023.models.enums;

import lombok.Getter;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Статус обращения
 */
@Getter
public enum AppealStatus {
    /**
     * Ожидает ответа от администратора
     */
    WAITING("Ожидает"),

    /**
     * Принято администратором
     */
    ACCEPTED("Принято"),

    /**
     * Отклонено администратором
     */
    REJECTED("Отклонено");
    private String ruName;

    AppealStatus(String ruName){
        this.ruName = ruName;
    }

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