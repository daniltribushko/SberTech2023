package com.example.sbertech2023.exceptions.violationtypes;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Исключение: Тип нарушения не найден
 */
public class ViolationTypeNotFoundException extends GlobalAppException {
    public ViolationTypeNotFoundException(Integer id) {
        super(404, "Violation type with " + id + " id not found");
    }
}
