package com.example.desafioquality.repository;

import com.example.desafioquality.dto.PropertyResponse;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.helper.DatabaseHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class PropertyRepository {


    private ObjectMapper objectMapper = DatabaseHelper.getObjectMapper();
    private String PATH = DatabaseHelper.getDatabasePathProperties();
    private List<Property> propertyList = new ArrayList(DatabaseHelper.getDatabaseProperty());

    public Property findById(Long id) {
        return propertyList.stream().filter(property -> property.getId().equals(id)).findFirst().orElse(new Property());
    }

    public List<Property> getAll() {
        return new ArrayList<>(propertyList);
    }

    public Property getOrdenedRooms(Long id){
       Property property = findById(id);
       property.setRooms(property.ordenedListRoom(property.getRooms()));
       return property;
    }

    public List<Property> findByName(String name) {
        return propertyList.stream().filter(property -> property.getName().equals(name)).collect(Collectors.toList());
    }

    public List<Property> findByNameAndDistrict(String name, String districtName) {
        return propertyList.stream().filter(property -> property.getName().equals(name))
                .filter(property -> property.getDistrict().getName().equals(districtName)).collect(Collectors.toList());
    }

    public void save(Property property) throws IOException {
        property.setId((long) propertyList.size() + 1);
        propertyList.add(property);
        updateFile();
    }

    public void updateFile() throws IOException {
        try {
            objectMapper.writeValue(new File(PATH), this.propertyList);
        } catch (IOException e) {
            throw new IOException("Erro leitura de arquivo");
        }

    }


}

