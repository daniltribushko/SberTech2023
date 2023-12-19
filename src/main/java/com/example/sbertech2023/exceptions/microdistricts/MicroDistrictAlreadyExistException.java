package com.example.sbertech2023.exceptions.microdistricts;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Исключение: Микрорайон уже сохранен
 */
public class MicroDistrictAlreadyExistException extends GlobalAppException {
    public MicroDistrictAlreadyExistException(String name) {
        super(409, "MicroDistrict " + name + "already exist");
    }
}
