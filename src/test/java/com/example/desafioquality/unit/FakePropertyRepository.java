package com.example.desafioquality.unit;

import com.example.desafioquality.unit.mocks.FakePropertyObject;

public class FakePropertyRepository {

    public FakeProperty findById(Long id) {
        return FakePropertyObject.makeFakeProperty();
    }
}
