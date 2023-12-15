package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Исключение: Обращение с указанным именем уже сохранено
 */
public class AppealAlreadyExistException extends GlobalAppException{
    public AppealAlreadyExistException(String name) {
        super(400, "Appeal " + name + " already exist");
    }
}
