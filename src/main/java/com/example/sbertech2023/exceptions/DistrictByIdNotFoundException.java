package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Исключение: Район с указанным id не найден
 */
public class DistrictByIdNotFoundException extends GlobalAppException{
    public DistrictByIdNotFoundException(Integer id) {
        super(404, "District with " + id + " id not found");
    }
}
