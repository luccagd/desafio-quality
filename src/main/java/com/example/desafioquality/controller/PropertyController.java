package com.example.desafioquality.controller;

import com.example.desafioquality.dto.PropertyRequest;
import com.example.desafioquality.dto.PropertyResponse;
import com.example.desafioquality.dto.PropertyRoomAreaResponse;
import com.example.desafioquality.dto.RoomDTO;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.service.PropertyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/area/{id}/total")
    public ResponseEntity<String> getTotalArea(@PathVariable Long id) {
        return ResponseEntity.ok().body("Area total: " + propertyService.getArea(id) + "m²");
    }

    @GetMapping("/price/{id}")
    public BigDecimal getPropertyValue(@PathVariable Long id) {
        PropertyResponse response = PropertyResponse.toResponse(propertyService.findById(id));
        return response.getTotal();
    }

    @GetMapping("/room/area/{id}")
    public ResponseEntity<List<PropertyRoomAreaResponse>> getAreaByRoom(@PathVariable Long id) {
        return ResponseEntity.ok().body(propertyService.getAreaByRoom(id));
    }

    @GetMapping("/room/biggest/{id}")
    public ResponseEntity<RoomDTO> getBiggestRoom(@PathVariable Long id) {
        return ResponseEntity.ok().body(RoomDTO.toDTO(propertyService.getBiggestRoom(id)));
    }

    @PostMapping("/post")
    public ResponseEntity<PropertyResponse> save(@Valid @RequestBody PropertyRequest propertyRequest,
        UriComponentsBuilder uriComponentsBuilder) throws IOException {
        propertyService.save(PropertyRequest.toEntity(propertyRequest));
        URI uri = uriComponentsBuilder.path("/create-request/get/{id}").buildAndExpand(propertyRequest.getId()).toUri();
        return ResponseEntity.created(uri).body(PropertyResponse.toResponse(propertyRequest));
    }

}
