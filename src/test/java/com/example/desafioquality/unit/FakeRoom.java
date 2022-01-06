package com.example.desafioquality.unit;

import java.util.Objects;

public class FakeRoom {
    private Long id;

    private String name;

    private Double width;

    private Double length;

    public FakeRoom(Long id, String name, Double width, Double length) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public Double getArea() {
        return width * length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FakeRoom fakeRoom = (FakeRoom) o;
        return Objects.equals(name, fakeRoom.name) && Objects.equals(width, fakeRoom.width) && Objects.equals(length, fakeRoom.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, width, length);
    }
}
