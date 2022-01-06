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
import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping(path = "/get", params = {"name"})
    public ResponseEntity<List<DistrictDTO>> getByName(@RequestParam(value = "name") String name){
        List<DistrictDTO> districtDTOList = DistrictDTO.listEntityToDTO(districtService.findByName(name));
        return ResponseEntity.ok().body(districtDTOList);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<DistrictDTO>> getAll(){
        List<DistrictDTO> districtDTOList = DistrictDTO.listEntityToDTO(districtService.getAll());
        return ResponseEntity.ok().body(districtDTOList);
    }

    @PostMapping("/post")
    public ResponseEntity<DistrictDTO> post(@Valid @RequestBody DistrictDTO districtDTO, UriComponentsBuilder uriComponentsBuilder) throws IOException {
        DistrictDTO dto = DistrictDTO.toDTO(districtService.save(districtDTO));
        URI uri = uriComponentsBuilder.path("/district/get/{name}").buildAndExpand(dto.getName()).toUri();
        return ResponseEntity.created(uri).body(districtDTO);
    }

    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }
}
