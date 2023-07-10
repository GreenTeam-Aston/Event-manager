package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import aston.greenteam.eventmanager.services.impl.ParametersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ParameterControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ParametersServiceImpl parametersServiceImpl;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders.
                standaloneSetup(new ParameterController(parametersServiceImpl)).build();
    }

    @Test
    void findByValueId() throws Exception {
        ParameterDTO parameterDTO = new ParameterDTO();
        parameterDTO.setId(2L);
        when(parametersServiceImpl.findById(2L)).thenReturn(parameterDTO);
        mockMvc.perform(get("/api/v1/parameters/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void findAll() throws Exception {
        ParameterDTO parameterDTO = new ParameterDTO();
        parameterDTO.setId(1L);
        List<ParameterDTO> parameterDTOList = new ArrayList<>();
        parameterDTOList.add(parameterDTO);
        when(parametersServiceImpl.findAll()).thenReturn(parameterDTOList);
        mockMvc.perform(get("/api/v1/parameters"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        ParameterDTO parameterDTO = new ParameterDTO();
        parameterDTO.setId(1L);
        parameterDTO.setName("test");

        when(parametersServiceImpl.createParameter(any(ParameterDTO.class))).thenReturn(parameterDTO);
        mockMvc.perform(post("/api/v1/parameters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(parameterDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    void update() throws Exception {
        Parameter parameter = new Parameter();
        parameter.setId(1L);
        parameter.setName("Parameter");

        ParameterDTO parameterDTO = new ParameterDTO();
        parameterDTO.setId(1L);
        parameterDTO.setName("updateParameter");

        when(parametersServiceImpl.updateParameter(any(Parameter.class))).thenReturn(parameterDTO);
        mockMvc.perform(put("/api/v1/parameters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(parameter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("updateParameter"));
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(parametersServiceImpl).deleteParameter(anyLong());

        mockMvc.perform(delete("/api/v1/parameters/1"))
                .andExpect(status().isOk());
    }

    @Test
    void assignProjectToEmployee() {
    }

    @Test
    public void testAssignValueToParameter() {
        Long parameterId = 1L;
        Long valueId = 2L;

        when(parametersServiceImpl.assignValueToParameter(parameterId, valueId)).thenReturn(new ParameterDTO());
        ParameterDTO parameterDTO = parametersServiceImpl.assignValueToParameter(parameterId, valueId);
        assertEquals(new ParameterDTO(), parameterDTO);
    }

}