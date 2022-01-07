package com.example.desafioquality.dto;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PropertyResponse {
    private Long id;

    @NotEmpty(message = "The field name must not be empty!")
    @NotNull(message = "The field name must not be null!")
    @Size(min = 1, max = 30, message = "The property name must have between 1 and 30 letters")
    @Min(value = 1, message = "The property name must have between 1 and 30 letters")
    @Max(value = 30, message = "The property name must have between 1 and 30 letters")
    private String name;

    private District district;
    private List<Room> rooms = new ArrayList<>();
    private BigDecimal total;

    public static PropertyResponse toResponse(Property property) {
        PropertyResponse response = PropertyResponse.builder()
                .id(property.getId())
                .name(property.getName())
                .district(property.getDistrict())
                .rooms(property.getRooms())
                .total(property.findFinalPrice()).build();
        return response;
    }

    public static PropertyResponse toResponse(PropertyRequest propertyRequest) {
        PropertyResponse response = PropertyResponse.builder()
                .id(propertyRequest.getId())
                .name(propertyRequest.getName())
                .district(propertyRequest.getDistrict())
                .rooms(propertyRequest.getRooms())
                .total(propertyRequest.findFinalPrice()).build();
        return response;
    }
}
