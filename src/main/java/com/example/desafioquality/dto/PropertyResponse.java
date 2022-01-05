package com.example.desafioquality.dto;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class PropertyResponse {

        private Long id;
        @NotEmpty(message = "The field name must not be empty!")
        @NotNull(message = "The field name must not be null!")
        @Size(min=1, max=30, message = "The property name must have between 1 and 30 letters")
        private String name;
        private District district;
        private List<RoomDTO> rooms = new ArrayList<>();
        private BigDecimal total;

        public static PropertyResponse toResponse(Property property)
        {
               PropertyResponse response = PropertyResponse.builder()
                        .id(property.getId())
                        .name(property.getName())
                        .district(property.getDistrict())
                        .rooms(RoomDTO.toListDTO(property.getRooms()))
                        .total(property.findFinalPrice()).build();
        return response;
        }

}
