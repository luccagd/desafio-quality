package com.example.desafioquality.service;

import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.exception.BusinessException;
import com.example.desafioquality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public List<Property> getAll() {
        return propertyRepository.getAll();
    }

    public List<Property> findAllByNameAndDistrict(String name, String districtName) {
        return propertyRepository.findByNameAndDistrict(name, districtName);
    }

    public List<Property> findByName(String name) {
        return propertyRepository.findByName(name);
    }

    public Room getBiggestRoom(Long id) {
        Property property = findById(id);

        return property.getBiggestRoom();
    }

    public Double getArea(Long id) {
        Property property = findById(id);

        return property.calculateArea();
    }

    public List<Room> getRoomsArea(Long id) {
        Property property = findById(id);

        return property.getRooms();
    }

    public void save(Property property) throws IOException {
        property.setDistrict(districtService.findById(property.getDistrict().getId()));
        propertyRepository.save(property);
    }
}