package com.example.desafioquality.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.transform.TransformingClassLoader;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    private String name;
    private Double width;
    private Double length;


   public Double calculateAreaRoom() {

    return this.width * this.length;
    }


}
