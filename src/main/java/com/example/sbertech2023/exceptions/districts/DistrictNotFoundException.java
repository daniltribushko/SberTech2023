package com.example.sbertech2023.exceptions.districts;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Исключение: Район не найден
 */
public class DistrictNotFoundException extends GlobalAppException {
    public DistrictNotFoundException(String name) {
        super(404, "District " + name + " not found");
    }
}
