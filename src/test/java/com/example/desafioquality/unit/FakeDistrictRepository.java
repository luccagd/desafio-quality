package com.example.desafioquality.unit;

import com.example.desafioquality.unit.mocks.FakeDistrictObject;

public class FakeDistrictRepository {

    public FakeDistrict findById(Long id) {
        return FakeDistrictObject.makeFakeDistrict();
    }
}
