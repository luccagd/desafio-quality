package com.example.desafioquality.unit.mocks;

import com.example.desafioquality.entity.Room;

public abstract class FakeRoomObject {

    public static Room makeFakeRoom() {
        return new Room("Valid Name", 1.0, 1.0);
    }

    public static Room makeFakeRoom(String name, Double width, Double length) {
        return new Room(name, width, length);
    }
}
