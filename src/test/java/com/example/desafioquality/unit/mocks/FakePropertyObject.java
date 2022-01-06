package com.example.desafioquality.unit.mocks;

import com.example.desafioquality.entity.Property;

import java.util.Arrays;

public class FakePropertyObject {

    public static Property makeFakeProperty() {
        return new Property(1L,
                "Valid Name",
                FakeDistrictObject.makeFakeDistrict(),
                Arrays.asList(
                        FakeRoomObject.makeFakeRoom("A", 1.0, 1.0),
                        FakeRoomObject.makeFakeRoom("B", 2.0, 2.0),
                        FakeRoomObject.makeFakeRoom("C", 3.0, 3.0)
                )
        );
    }
}
