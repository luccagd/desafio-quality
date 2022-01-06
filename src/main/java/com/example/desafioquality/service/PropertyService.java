package com.example.desafioquality.service;

import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.repository.DistrictRepository;
import com.example.desafioquality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private DistrictService districtService;

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
    }

    public Room getBiggestRoom(Long id) {
      return propertyRepository.biggestRoom(id);
    }


  public void save(Property property) throws IOException  {
        property.setDistrict(districtService.findById(property.getDistrict().getId()));
        propertyRepository.save(property);
  }
}