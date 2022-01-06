package com.example.desafioquality.repository;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.helper.DatabaseHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DistrictRepository {
    private ObjectMapper objectMapper = DatabaseHelper.getObjectMapper();
    private String PATH = DatabaseHelper.getDatabasePathDistricts();
    private List<District> districtList = new ArrayList();

    public List<District> getAll(){
        return districtList;
    }

    public District findByName(String name){
        return districtList.stream().filter(district -> district.getName().equals(name)).findFirst().orElse(null);
    }

    public District save(District district) throws IOException {
        district.setId((long) districtList.size() + 1);
        districtList.add(district);
        updateFile();
        return district;
    }

    public void updateFile() throws IOException{
        try {
            objectMapper.writeValue(new File(PATH), this.districtList);
        }catch (IOException e){
            throw new IOException("Erro leitura de arquivo");
        }
    }

    public District findById(Long id) {
        return districtList.stream().filter(district -> district.getId().equals(id)).findFirst().orElse(null);
    }
}
