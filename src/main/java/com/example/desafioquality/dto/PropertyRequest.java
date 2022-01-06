package com.example.desafioquality.dto;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PropertyRequest {
    private Long id;

    @NotEmpty(message = "The field name must not be empty!")
    @NotNull(message = "The field name must not be null!")
    @Size(min = 1, max = 30, message = "The property name must have between 1 and 30 letters")
    private String name;

    private District district;

    private List<Room> rooms = new ArrayList<>();

    public static Property toEntity(PropertyRequest propertyRequest) {
        Property property = Property.builder()
                .id(propertyRequest.getId())
                .name(propertyRequest.getName())
                .district(propertyRequest.getDistrict())
                .rooms(propertyRequest.getRooms())
                .build();
        return property;
    }
}
