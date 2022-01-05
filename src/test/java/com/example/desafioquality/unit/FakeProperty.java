package com.example.desafioquality.unit;


import java.util.List;


public class FakeProperty {
    private Long id;

    private String name;

    private FakeDistrict district;

    private List<FakeRoom> rooms;

    public FakeProperty(Long id, String name, FakeDistrict district, List<FakeRoom> rooms) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.rooms = rooms;
    }

    public Double getArea() {
        return 15.0;
    }

    public List<FakeRoom> getRooms() {
        return rooms;
    }
}
