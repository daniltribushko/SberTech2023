package com.example.sbertech2023.exceptions.appeals;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Trtibushko Danil
 * @since 14.12.2023
 *
 * Исключение: Обращение с указанным именем не найдено
 */
public class AppealByNameNotFoundException extends GlobalAppException {
    public AppealByNameNotFoundException(String name) {
        super(404, "Appeal " + name + " not found");
    }
}
