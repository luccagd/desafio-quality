package com.example.desafioquality.dto;

import com.example.desafioquality.entity.District;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DistrictDTO {

    private Long id;

    @NotEmpty(message = "The field name must not be empty!")
    @NotNull(message = "The field name must not be null!")
    @Size(min = 1, max = 45, message = "The district name must have between 1 and 45 letters")
    private String name;

    @Digits(fraction = 2, integer = 10)
    private BigDecimal squareMeterPrice;

    public static DistrictDTO toDTO(District district) {
        DistrictDTO dto = DistrictDTO.builder()
                .id(district.getId())
                .name(district.getName())
                .squareMeterPrice(district.getSquareMeterPrice())
                .build();

        return dto;
    }
}
