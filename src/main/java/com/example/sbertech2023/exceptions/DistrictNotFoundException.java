package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Исключение: Район не найден
 */
public class DistrictNotFoundException extends GlobalAppException{
    public DistrictNotFoundException(String name) {
        super(404, "District " + name + " not found");
    }
}
