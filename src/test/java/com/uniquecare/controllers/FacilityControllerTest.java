package com.uniquecare.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniquecare.models.Categories;
import com.uniquecare.models.Facility;
import com.uniquecare.services.FacilityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FacilityControllerTest {

    @Mock
    private FacilityServiceImpl facilityService;

    @InjectMocks
    private FacilityController facilityController;

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    Facility facility1;
    Facility facility2;
    Facility facility3;
    Facility facilityWithoutId;
    List<Facility> facilityList = new ArrayList<>();


    @BeforeEach
    public void setup(){
        Categories categories = new Categories(1L, "Cuidados");
        Set<Categories> categoriesList = new HashSet<>();
        categoriesList.add(categories);

        facility1 = new Facility(1L, "Facility 1", "servicio 1", 10,null, null);
        facility2 = new Facility(2L, "Facility 2", "servicio 2", 10, null,null);
        facility3 = new Facility(3L, "Facility 3", "servicio 3", 10, null,null);
        facilityWithoutId = new Facility("Facility 4", "servicio 4", 10, null,null);

        facilityList.add(facility1);
        facilityList.add(facility2);
        facilityList.add(facility3);

        mockMvc = MockMvcBuilders.standaloneSetup(facilityController).build();
    }

    @Test
    void findFacilityById() throws Exception {
        when(facilityService.findFacilityById(any())).thenReturn(facility1);
        mockMvc.perform((MockMvcRequestBuilders.get("/api/facility/single/"+facility1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(facility1))))
                .andDo(MockMvcResultHandlers.print());
        verify(facilityService).findFacilityById(any());
        verify(facilityService, times(1)).findFacilityById(facility1.getId());
    }

    @Test
    void getFacility() throws Exception {
        when(facilityService.getAllFacilities()).thenReturn(facilityList);
        mockMvc.perform((MockMvcRequestBuilders.get("/api/facility/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(facilityList))))
                .andDo(MockMvcResultHandlers.print());
        verify(facilityService).getAllFacilities();
        verify(facilityService, times(1)).getAllFacilities();
    }

    @WithMockUser
    @Test
    void doentExpectToAddFacilityWithoutAuthentication() throws Exception{
        mockMvc.perform((MockMvcRequestBuilders.post("/api/facility/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(facilityWithoutId))))
                        .andExpect(status().isBadRequest());
        verify(facilityService, times(0)).addNewFacility(any());
    }

    @Test
    void editFacility() throws Exception {
        when(facilityService.updateFacility(any())).thenReturn(facility2);
        mockMvc.perform((MockMvcRequestBuilders.put("/api/facility/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(facility2))))
                .andExpect(status().isCreated());
        verify(facilityService, times(1)).updateFacility(any());
    }

    @Test
    void deleteFacilityById() throws Exception{
        mockMvc.perform((MockMvcRequestBuilders.delete("/api/facility/delete/"+facility1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(facility1))))
                        .andExpect(status().isOk());
        verify(facilityService, times(1)).deleteFacilityById(any());
    }
}