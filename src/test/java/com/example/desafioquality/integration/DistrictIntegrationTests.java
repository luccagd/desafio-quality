package com.example.desafioquality.integration;

import com.example.desafioquality.controller.DistrictController;
//import org.junit.jupiter.api.Assertions;
import com.example.desafioquality.dto.DistrictDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class DistrictIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DistrictController districtController;


    public DistrictDTO makeFakeDistrictDTO(String name)
    {
        return DistrictDTO.builder().name(name).squareMeterPrice(BigDecimal.valueOf(20)).build();
    }
    //district/post

    @Test
    public void shouldBeCreateDistrict() throws Exception {
        mockMvc.perform(post("/district/post")
                .content(asJsonString(makeFakeDistrictDTO("Rua X")))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Rua X"));
    }

    @Test
    public void shouldBeNotCreateDistrict() throws Exception {
        mockMvc.perform(post("/district/post")
                .content(asJsonString(makeFakeDistrictDTO("")))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.status").value("UNPROCESSABLE_ENTITY"));
    }

    public static String asJsonString(Object object)
    {
        try {
            return new ObjectMapper().writeValueAsString(object);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
