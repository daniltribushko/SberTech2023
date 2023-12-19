package com.example.sbertech2023.exceptions.appeals;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Исключение: Обращение с указанным id не найдено
 */
public class AppealByIdNotFoundException extends GlobalAppException {
    public AppealByIdNotFoundException(Long id) {
        super(404, "Appeal with " + id + " id not found");
    }
}
