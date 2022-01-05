package com.example.desafioquality.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Property {

    private Long id;
    @NotEmpty (message = "The field name must not be empty!")
    @NotNull (message = "The field name must not be null!")
    @Size(min=1, max=30, message = "The property name must have between 1 and 30 letters")
    private String name;
    private District district;
    private List<Room> rooms = new ArrayList<>();

    public Double getArea(){
        return rooms.stream().mapToDouble(room -> room.getArea().doubleValue()).sum();
    }

    public BigDecimal findFinalPrice() {
        return new BigDecimal(getArea() * district.getSquareMeterPrice().doubleValue()).setScale(2);
    }

}

