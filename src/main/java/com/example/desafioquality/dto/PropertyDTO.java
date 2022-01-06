package com.example.desafioquality.dto;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class PropertyDTO {

    private Long id;
    @NotEmpty(message = "The field name must not be empty!")
    @NotNull(message = "The field name must not be null!")
    @Size(min = 1, max = 30, message = "The property name must have between 1 and 30 letters")
    @Min(value = 1, message = "The property name must have between 1 and 30 letters")
    @Max(value = 30, message = "The property name must have between 1 and 30 letters")

    private String name;
    private District district;
    private List<RoomDTO> rooms = new ArrayList<>();
    private BigDecimal total;

    public static PropertyDTO toResponse(Property property) {
        PropertyDTO response = PropertyDTO.builder()
                .id(property.getId())
                .name(property.getName())
                .district(property.getDistrict())
                .rooms(RoomDTO.toListDTO(property.getRooms()))
                .total(property.findFinalPrice()).build();
        return response;
    }

    public static Property toEntity(PropertyDTO propertyDTO) {
        Property property = Property.builder()
                .id(propertyDTO.getId())
                .name(propertyDTO.getName())
                .district(propertyDTO.getDistrict())
                .rooms(RoomDTO.toListEntity(propertyDTO.getRooms())).build();
        return property;
    }

}
