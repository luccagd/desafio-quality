package com.example.desafioquality.controller;

import com.example.desafioquality.dto.PropertyResponse;
import com.example.desafioquality.dto.RoomDTO;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.repository.PropertyRepository;
import com.example.desafioquality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyRepository repository;

    @GetMapping("/area/{id}/total")
    public ResponseEntity <String> getTotalArea(@PathVariable Long id)
    {
        return ResponseEntity.ok().body("Area total: " + propertyService.findById(id).getArea() + "m²") ;//ok
    }

    @GetMapping("/price/{id}")
    public BigDecimal getPropertyValue(@PathVariable Long id)
    {
        PropertyResponse response = PropertyResponse.toResponse(propertyService.findById(id));
        return response.getTotal();
    }

    @GetMapping("/room/area/{id}")
    public ResponseEntity<List<RoomDTO>> getAreaByRoom(@PathVariable Long id)
    {
        PropertyResponse response = PropertyResponse.toResponse(propertyService.findById(id));
        return ResponseEntity.ok().body(response.getRooms());
    }

    @GetMapping("/room/{id}/ordened")
    public Property getOrdenedRooms(@PathVariable Long id) {
        return repository.getOrdenedRooms(id);
    }
}
