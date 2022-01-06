package com.example.desafioquality.repository;

import com.example.desafioquality.dto.DistrictDTO;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.helper.DatabaseHelper;
import com.example.desafioquality.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public List<Property> findByName(String name) {
        return propertyList.stream().filter(property -> property.getName().equals(name)).collect(Collectors.toList());
    }

    public List<Property> findByNameAndDistrict(String name, String districtName) {
        return propertyList.stream().filter(property -> property.getName().equals(name))
                .filter(property -> property.getDistrict().getName().equals(districtName)).collect(Collectors.toList());
    }

    public void save(Property property) throws IOException {
        try {
            property.setId((long) propertyList.size() + 1);
            propertyList.add(property);
            updateFile();
        }catch (IOException e){
            throw new IOException("Erro na leitura do arquivo");
        }
    }

    public void updateFile() throws IOException {
        try {
            objectMapper.writeValue(new File(PATH), this.propertyList);
        } catch (IOException e) {
            throw new IOException("Erro leitura de arquivo");
        }
    }

    public Room biggestRoom(Long id) {
        Room room = findById(id).getBiggestRoom(findById(id).getRooms());
        return room;
    }
}

