package com.example.sbertech2023.exceptions.districts;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Исключение: Район уже сохранен
 */
public class DistrictAlreadyExistException extends GlobalAppException {
    public DistrictAlreadyExistException(String name) {
        super(409, "District " + name + " already exist");
    }
}
