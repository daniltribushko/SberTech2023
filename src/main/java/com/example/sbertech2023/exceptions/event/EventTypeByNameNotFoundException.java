package com.example.sbertech2023.exceptions.event;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 08.01.2024
 *
 * Исключение: Тип мероприятия по указанному имени не найден
 */
public class EventTypeByNameNotFoundException extends GlobalAppException {
    public EventTypeByNameNotFoundException(String name) {
        super(404, "Event type " + name + " not found");
    }
}
