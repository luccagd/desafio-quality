package com.example.desafioquality.dto;

import com.example.desafioquality.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoomDTO {

    @NotEmpty(message = "The field name must not be empty!")
    @NotNull(message = "The field name must not be null!")
    @Size(min = 1, max = 45, message = "The district name must have between 1 and 45 letters")
    private String name;
    @NotEmpty(message = "O Comprimento do cômodo não pode estar vazio.")
    @Min(value = 1, message = "")
    @Max(value = 33, message = "")
    private double length;
    @NotEmpty(message = "A largura do cômodo não pode estar vazia ")
    @Min(value = 1, message = "")
    @Max(value = 30, message = "The Room name must have between 1 and 30 letters")
    private double width;
    private BigDecimal area;

   public static RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = RoomDTO.builder()
                .name(room.getName())
                .length(room.getLength())
                .width(room.getWidth())
                .area(new BigDecimal(room.calculateRoomArea()).setScale(2)).build();
        return roomDTO;
    }
    //OLHAR PARA O ID
    public static Room toEntity(RoomDTO roomDTO) {
        Room room = Room.builder()
                .name(roomDTO.getName())
                .length(roomDTO.getLength())
                .width(roomDTO.getWidth())
                .build();
        return room;
    }

    public static List<RoomDTO> toListDTO(List<Room> roomList) {
        return roomList.stream().map(RoomDTO::toDTO).collect(Collectors.toList());
    }

    public static List<Room> toListEntity(List<RoomDTO> roomListDTO) {
        return roomListDTO.stream().map(RoomDTO::toEntity).collect(Collectors.toList());
    }



}
