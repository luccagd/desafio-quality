package com.example.desafioquality.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class District {

    @NotEmpty(message = "The field name must not be empty!")
    @NotNull(message = "The field name must not be null!")
    @Size(min=1, max=45, message = "The district name must have between 1 and 45 letters")
    private String name;
    @Size(min = 1, max = 13, message = "The square meter price must have the max of 13 digits!")
    private BigDecimal squareMeterPrice;
}
