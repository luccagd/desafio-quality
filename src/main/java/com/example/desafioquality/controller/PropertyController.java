package com.example.desafioquality.controller;
import com.example.desafioquality.dto.PropertyRequest;
import com.example.desafioquality.dto.PropertyResponse;
import com.example.desafioquality.dto.RoomDTO;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

   @GetMapping("/area/{id}/total")
   public ResponseEntity <String> getTotalArea(@PathVariable Long id)
   {
       return ResponseEntity.ok().body("Area total: " + propertyService.findById(id).calculateArea() + "m²") ;//ok
    }

    @GetMapping("/price/{id}")
    public BigDecimal getPropertyValue(@PathVariable Long id)
    {
        PropertyResponse response = PropertyResponse.toResponse(propertyService.findById(id));
        return response.getTotal();
    }

    @GetMapping("/room/area/{id}")
    public ResponseEntity<List<Room>> getAreaByRoom(@PathVariable Long id)
    {
        PropertyResponse response = PropertyResponse.toResponse(propertyService.findById(id));
        return ResponseEntity.ok().body(response.getRooms());
    }
    @GetMapping("/room/biggest/{id}")
    public ResponseEntity<RoomDTO> getBiggestRoom(@PathVariable Long id) {
        return ResponseEntity.ok().body(RoomDTO.toDTO(propertyService.getBiggestRoom(id)));
    }
    @PostMapping
    public ResponseEntity <PropertyResponse> save(@RequestBody PropertyRequest propertyRequest, UriComponentsBuilder uriComponentsBuilder) throws IOException {
        Property property = PropertyRequest.toEntity(propertyRequest);
        propertyService.save(property);
        URI uri = uriComponentsBuilder.path("/create-request/get/{id}").buildAndExpand(property.getId()).toUri();
        return ResponseEntity.created(uri).body(PropertyResponse.toResponse(property));
    }

}
