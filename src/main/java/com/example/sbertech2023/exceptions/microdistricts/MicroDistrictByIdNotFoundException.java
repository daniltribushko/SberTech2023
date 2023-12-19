package com.example.sbertech2023.exceptions.microdistricts;

import com.example.sbertech2023.exceptions.GlobalAppException;

public class MicroDistrictByIdNotFoundException extends GlobalAppException {
    public MicroDistrictByIdNotFoundException(Integer id) {
        super(404, "MicroDistrict with " + id + " id not found");
    }
}
