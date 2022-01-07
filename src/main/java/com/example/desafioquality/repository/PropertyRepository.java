package com.example.desafioquality.repository;

import com.example.desafioquality.entity.Property;
import com.example.desafioquality.helper.DatabaseHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PropertyRepository {
    private ObjectMapper objectMapper = DatabaseHelper.getObjectMapper();
    private String PATH = DatabaseHelper.getDatabasePathProperties();
    private List<Property> propertyList = new ArrayList<>(DatabaseHelper.getDatabaseProperty());

    public Property findById(Long id) {
        return propertyList.stream().filter(property -> property.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Property property) throws IOException {
        try {
            property.setId((long) propertyList.size() + 1);

            propertyList.add(property);
            updateFile();
        } catch (IOException e) {
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

    public List<Property> getAll() {
        return propertyList;
    }
}
