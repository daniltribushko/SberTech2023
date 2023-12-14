package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Исключение: Тип нарушения уже сохранен
 */
public class ViolationTypeAlreadyExistException extends GlobalAppException{
    public ViolationTypeAlreadyExistException(String name) {
        super(409, "Violation type " + name + " already exist");
    }
}
