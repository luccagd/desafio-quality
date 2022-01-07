package com.example.desafioquality.dto;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PropertyRequest {
    private Long id;
    @NotBlank
    @NotEmpty(message = "The field name must not be empty!")
    @NotNull(message = "The field name must not be null!")
    @Size(min = 1, max = 30, message = "The property name must have between 1 and 30 letters")
    private String name;

    @NotNull(message = "The field name must not be null!")
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

    public Double calculateArea(){
        return rooms.stream().mapToDouble(room -> room.calculateRoomArea().doubleValue()).sum();
    }
    public BigDecimal findFinalPrice() {
        return new BigDecimal(calculateArea() * district.getSquareMeterPrice().doubleValue()).setScale(2);
    }
}
