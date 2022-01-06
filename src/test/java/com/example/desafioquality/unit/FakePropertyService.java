package com.example.desafioquality.unit;

import java.util.List;

public class FakePropertyService {

    private FakePropertyRepository fakePropertyRepository;

    public FakePropertyService(FakePropertyRepository fakePropertyRepository) {
        this.fakePropertyRepository = fakePropertyRepository;
    }

    public FakeProperty findById(Long id) {
        if (id < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        FakeProperty fakeProperty = fakePropertyRepository.findById(id);
        if (fakeProperty == null) {
            throw new ArithmeticException();
        }

        return fakeProperty;
    }


    public Double calculateArea(Long id) {
        FakeProperty fakeProperty = findById(id);

        return fakeProperty.getArea();
    }

    public FakeRoom getBiggestRoom(Long id) {
        FakeProperty fakeProperty = findById(id);

        return fakeProperty.getRooms().get(2);
    }

    public List<FakeRoom> getRoomsAreas(Long id) {
        FakeProperty fakeProperty = findById(id);

        return fakeProperty.getRooms();
    }

}
