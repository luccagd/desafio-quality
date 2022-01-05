package com.example.desafioquality.service;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.repository.PropertyRepository;
import com.example.desafioquality.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;



    public Property findById(Long id)
    {
        return propertyRepository.findById(id);
    }
    public List<Property> getAll() {

        return propertyRepository.getAll();
    }

    public List<Property> findAllByNameAndDistrict(String name, String districtName) {
        return propertyRepository.findByNameAndDistrict(name, districtName);
    }

    public List<Property> findByName(String name){
        return propertyRepository.findByName(name);

        //return propertyRepository.getAll().(property -> property.getName().equals(name)).collect(Collectors.toList());
    }
    public Room GetRoomBiggest(String name)
    {

        Room room2 = new Room();
       // findByName(name).stream().filter(property -> property.getRooms().stream().forEach(room -> room.getArea().compareTo(room2.getArea())))
    return null;
    }


}