package com.example.desafioquality.service;

import com.example.desafioquality.dto.PropertyRoomAreaResponse;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.exception.BusinessException;
import com.example.desafioquality.repository.PropertyRepository;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PropertyService {

    private DistrictService districtService;

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository, DistrictService districtService) {
        this.propertyRepository = propertyRepository;
        this.districtService = districtService;
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

    public Room getBiggestRoom(Long id) {
        Property property = findById(id);

        return property.findBiggestRoom();
    }

    public Double getArea(Long id) {
        Property property = findById(id);

        return property.calculateArea();
    }

    public List<Room> getRoomsArea(Long id) {
        Property property = findById(id);

        return property.getRooms();
    }

    public List<PropertyRoomAreaResponse> getAreaByRoom(Long id) {
        List<PropertyRoomAreaResponse> response = new ArrayList<>();

        Property property = findById(id);

        property.getRooms().forEach(room -> {
            response.add(new PropertyRoomAreaResponse(room.getName(), room.calculateRoomArea()));
        });

        return response;
    }

    public void save(Property property) throws IOException {
        property.setDistrict(districtService.findById(property.getDistrict().getId()));

        propertyRepository.save(property);
    }

    public List<Property> getAll() {
        return propertyRepository.getAll();
    }
}