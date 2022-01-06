package com.example.desafioquality.dto;

import com.example.desafioquality.entity.District;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DistrictDTO {

    private Long id;
    @NotEmpty(message = "The field name must not be empty!")
    @NotNull(message = "The field name must not be null!")
    @Size(min=1, max=45, message = "The district name must have between 1 and 45 letters")
    private String name;
    @Range(min=1,max=13,message = "The district name must have between 1 and 13 letters")
    private BigDecimal squareMeterPrice;

    public static DistrictDTO toDTO(District district) {
        DistrictDTO dto = DistrictDTO.builder().name(district.getName()).squareMeterPrice(district.getSquareMeterPrice()).build();
        return dto;
    }

    public static District toEntity(DistrictDTO dto)
    {
        return District.builder()
                .name(dto.getName())
                .squareMeterPrice(dto.getSquareMeterPrice())
                .build();
    }

    public static List<DistrictDTO> listEntityToDTO(List<District> districtList){
        return districtList.stream().map(DistrictDTO::toDTO).collect(Collectors.toList());
    }
}
