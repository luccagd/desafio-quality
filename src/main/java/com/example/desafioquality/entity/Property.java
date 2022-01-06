package com.example.desafioquality.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Property {

    private Long id;

    private String name;

    private District district;

    private List<Room> rooms = new ArrayList<>();

    public Double calculateArea(){
       return rooms.stream().mapToDouble(room -> room.calculateRoomArea().doubleValue()).sum();
    }

    public BigDecimal findFinalPrice() {
        return new BigDecimal(calculateArea() * district.getSquareMeterPrice().doubleValue()).setScale(2);
    }

    public Room getBiggestRoom(){
        return rooms.stream().max(Comparator.comparing(room -> room.calculateRoomArea())).get();
    }
}

