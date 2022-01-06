package com.example.desafioquality.service;

import com.example.desafioquality.dto.DistrictDTO;
import com.example.desafioquality.entity.District;
import com.example.desafioquality.exception.BusinessException;
import com.example.desafioquality.repository.DistrictRepository;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class DistrictService {

    private DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public District findById(Long id) {
        if (id < 1) {
            throw new BusinessException("Id should be greater than zero");
        }

        District district = districtRepository.findById(id);
        if (district == null) {
            throw new NoSuchElementException("District not found for the id passed as parameter");
        }

        return district;
    }

    public District save(DistrictDTO districtDTO) throws IOException {
        District district = District.builder()
                .name(districtDTO.getName())
                .squareMeterPrice(districtDTO.getSquareMeterPrice()).build();

        return districtRepository.save(district);
    }
}
