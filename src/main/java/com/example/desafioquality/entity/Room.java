package com.example.desafioquality.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.transform.TransformingClassLoader;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Room implements Comparable<Room> {

    private Long id;
    @NotEmpty(message = "O campo não pode estar vazio")
    @Size (max = 30,message = "O comprimento do cômodo não pode exceder 30 caracteres" )
    @Pattern(regexp = "[A-Z]+(.)*",message = "Nome deve iniciar com a letra maiscula")
    private String name;
    @NotEmpty(message = "A largura do cômodo não pode estar vazia ")
    @Size(max = 25,message = "A largura maxima permitida é de 25 metros")
    private Double width;
    @NotEmpty(message = "O Comprimento do cômodo não pode estar vazio.")
    @Size(max = 33, message = "O Comprimento maximo permitido por cômodo é de 33 metros")
    private Double length;


    public Double getArea() {

    return this.width * this.length;
    }

    @Override
    public int compareTo(Room room) {
        return Double.compare(this.getArea(), room.getArea());
    }
}
