package com.example.desafioquality.service;

import com.example.desafioquality.dto.DistrictDTO;
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

    public District findById(Long id)
    {
        District district = districtRepository.findById(id);

        if(district == null){
            throw new RuntimeException("ERRO");
        }
        return district;

    }
    public List<District> getAll(){
        return districtRepository.getAll();
    }

    public List<District> findByName(String name){
        return districtRepository.findByName(name);
    }

    public District save(DistrictDTO districtDTO) throws IOException {
        District district = District.builder()
                .name(districtDTO.getName())
                .squareMeterPrice(districtDTO.getSquareMeterPrice()).build();
       return districtRepository.save(district);
    }


}
