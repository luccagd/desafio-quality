package com.example.desafioquality.controller;

import com.example.desafioquality.dto.DistrictDTO;
import com.example.desafioquality.service.DistrictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping("/post")
    public ResponseEntity<DistrictDTO> post(@Valid @RequestBody DistrictDTO districtDTO, UriComponentsBuilder uriComponentsBuilder) throws IOException {
        DistrictDTO dto = DistrictDTO.toDTO(districtService.save(districtDTO));
        URI uri = uriComponentsBuilder.path("/district/get/{name}").buildAndExpand(dto.getName()).toUri();
        
        return ResponseEntity.created(uri).body(dto);
    }
}
