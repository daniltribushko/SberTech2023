package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Исключение: Тип нарушения с указанным именем не найден
 */
public class ViolationTypeByNameNotFoundException extends GlobalAppException{
    public ViolationTypeByNameNotFoundException(String name) {
        super(404, "Violation type " + name + " not found");
    }
}
