package com.example.sbertech2023.exceptions.microdistricts;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Исключение: Микрорайон не найден
 */
public class MicroDistrictNotFoundException extends GlobalAppException {
    public MicroDistrictNotFoundException(String name) {
        super(404, "MicroDistrict " + name + " not found");
    }
}
