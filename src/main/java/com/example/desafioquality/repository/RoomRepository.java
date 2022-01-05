package com.example.desafioquality.repository;

import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.helper.DatabaseHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepository {
    private ObjectMapper objectMapper = DatabaseHelper.getObjectMapper();
    private String PATH = DatabaseHelper.getDatabasePathProperties();
    private List<Room> ListRooms = new ArrayList(DatabaseHelper.getDatabaseProperty());

    public List<Room> getAll()
    {
        return new ArrayList<>(ListRooms);
    };
    public List<Property> findbyId(long id)
    {
        return (List<Property>) ListRooms.stream().filter(rooms ->rooms.getId().equals(id)).findFirst().get();
    }



}
