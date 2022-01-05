package com.example.desafioquality.unit.mocks;

import com.example.desafioquality.unit.FakeDistrict;

import java.math.BigDecimal;

public abstract class FakeDistrictObject {

    public static FakeDistrict makeFakeDistrict() {
        return new FakeDistrict("Valid Name", new BigDecimal(1.0));
    }
}
