package com.example.desafioquality.service;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService {


    @Autowired
    private DistrictRepository districtRepository;

    public List<District> getAll(){
        return districtRepository.getAll();
    }

    public List<District> findByName(String name){
        return districtRepository.findByName(name);
    }

    public void save(District district) throws IOException {
        districtRepository.save(district);
    }


}
