package com.example.desafioquality.unit.mocks;

import com.example.desafioquality.entity.District;

import java.math.BigDecimal;

public abstract class FakeDistrictObject {

    public static District makeFakeDistrict() {
        return new District(1L, "Valid Name", new BigDecimal(1.0));
    }
}
