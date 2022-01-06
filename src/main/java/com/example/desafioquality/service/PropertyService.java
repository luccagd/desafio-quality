package com.example.desafioquality.service;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.exception.BusinessException;
import com.example.desafioquality.repository.PropertyRepository;
import com.example.desafioquality.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property findById(Long id) {
        if (id < 1) {
            throw new BusinessException("Id should be greater than zero");
        }

        Property property = propertyRepository.findById(id);
        if (property == null) {
            throw new NoSuchElementException("District not found for the id passed as parameter");
        }

        return property;
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
    
    public void save(Property property) throws IOException  {
        propertyRepository.save(property);
    }

    public Room getBiggestRoom(Long id) {
        Property property = findById(id);

        return property.getBiggestRoom();
    }

    public Double getArea(Long id) {
        Property property = findById(id);

        return property.getArea();
    }

    public List<Room> getRoomsArea(Long id) {
        Property property = findById(id);

        return property.getRooms();
    }
}