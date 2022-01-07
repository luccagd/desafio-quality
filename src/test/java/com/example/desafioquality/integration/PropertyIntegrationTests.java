package com.example.desafioquality.integration;

import com.example.desafioquality.controller.PropertyController;
import com.example.desafioquality.dto.DistrictDTO;
import com.example.desafioquality.dto.PropertyRequest;
import com.example.desafioquality.dto.RoomDTO;
import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.repository.PropertyRepository;
import com.example.desafioquality.service.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PropertyController propertyController;

    @Autowired
    private PropertyService propertyService;


    @Test
    public void contextLoads(){
        Assertions.assertThat(propertyController).isNotNull();
    }

    public DistrictDTO makeFakeDistrictDTO(){
        return DistrictDTO.builder().id(1L).name("teste").squareMeterPrice(BigDecimal.valueOf(20)).build();
    }

    public List<RoomDTO> makeFakeRoomDTO(){
        List<RoomDTO> roomDTOList = new ArrayList<>();
        roomDTOList.add(RoomDTO.builder().name("Quarto").length(5).width(5).build());
        return roomDTOList;
    }

    public PropertyRequest makeFakePropertyResponse(String name) {
        return PropertyRequest.builder().name(name).district(DistrictDTO.toEntity(makeFakeDistrictDTO())).rooms(RoomDTO.listToEntity(makeFakeRoomDTO())).build();
    }

    @Test
    public void deveRetornarAreaPorQuarto() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/property/room/area/{id}", propertyService.getAll().size()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void naoDeveRetornarAreaPorQuarto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/property/room/area/{id}",  propertyService.getAll().size() + 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
    @Test
    public void deveRetornarAreaDaPropriedade() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/property/area/{id}/total", propertyService.getAll().size()))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
    }

    @Test
    public void naoDeveRetornarAreaDaPropriedade() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/property/area/{id}/total",  propertyService.getAll().size() + 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    public void shouldBeCreateProperty() throws Exception {
        mockMvc.perform(post("/property/post")
                .content(asJsonString(makeFakePropertyResponse("Chacara Everton")))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    //Rever
    @Test
    public void shouldBeNotCreateProperty() throws Exception {
        mockMvc.perform(post("/property/post")
                .content(asJsonString(makeFakePropertyResponse("")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.status").value("UNPROCESSABLE_ENTITY"));
    }

    @Test
    public void deveRetornarPreco() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/property/price/{id}",  propertyService.getAll().size()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


    @Test
    public void naoDeveRetornarPreco() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/property/price/{id}",  propertyService.getAll().size() + 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    public void deveRetornarMaiorQuarto() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/property/room/biggest/{id}",  propertyService.getAll().size()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void naoDeveRetornarMaiorQuart() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/property/room/biggest/{id}",  propertyService.getAll().size() + 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
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
