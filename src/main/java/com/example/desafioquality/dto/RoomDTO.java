package com.example.desafioquality.dto;

import com.example.desafioquality.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoomDTO {

    private String name;
    private double length;
    private double width;
    private BigDecimal area;

     public static RoomDTO toDTO(Room room)
     {
         RoomDTO roomDTO = RoomDTO.builder()
                 .name(room.getName())
                 .length(room.getLength())
                 .width(room.getWidth())
                 .area(new BigDecimal(room.getArea()).setScale(2)).build();
        return roomDTO;
     }
     
     public static List<RoomDTO> toListDTO(List<Room> roomList)
     {
         return roomList.stream().map(RoomDTO::toDTO).collect(Collectors.toList());
     }
}
