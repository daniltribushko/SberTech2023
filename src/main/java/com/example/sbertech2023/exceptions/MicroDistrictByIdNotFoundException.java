package com.example.sbertech2023.exceptions;

public class MicroDistrictByIdNotFoundException extends GlobalAppException{
    public MicroDistrictByIdNotFoundException(Integer id) {
        super(404, "MicroDistrict with " + id + " id not found");
    }
}
