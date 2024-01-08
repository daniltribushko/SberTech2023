package com.example.sbertech2023.exceptions.event;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 08.01.2024
 *
 * Исключение: Тип мероприятия с указанным идентификатором не найден
 */
public class EventTypeByIdNotFoundException extends GlobalAppException {
    public EventTypeByIdNotFoundException(Integer id) {
        super(404, "Event type with " + id + " id not found");
    }
}
