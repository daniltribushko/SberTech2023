package com.example.sbertech2023.models.enums;

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
    REJECTED
}