package com.example.sbertech2023.exceptions.event;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 08.01.2024
 *
 * Исключение: Тип мероприятия уже сохранён
 */
public class EventTypeAlreadyExistException extends GlobalAppException {
    public EventTypeAlreadyExistException(String name) {
        super(400, "Event type " + name + " already exist");
    }
}
