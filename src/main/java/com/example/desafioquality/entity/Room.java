package com.example.desafioquality.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    private String name;
    private Double width;
    private Double length;


   public Double calculateRoomArea() {

    return this.width * this.length;
    }

}
