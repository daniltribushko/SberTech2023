package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 15.12.2023
 *
 * Исключение: Микрорайон не находится в указанном районе
 */
public class MicroDistrictIsNotInDistrictException extends GlobalAppException{
    public MicroDistrictIsNotInDistrictException( String microDistrict) {
        super(409, "Microdistrict " + microDistrict + " is not in district");
    }
}
