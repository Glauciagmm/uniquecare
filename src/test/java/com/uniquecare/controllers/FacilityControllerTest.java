package com.uniquecare.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniquecare.models.Facility;
import com.uniquecare.services.FacilityServiceImpl;
import com.uniquecare.services.IUserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import java.util.List;

import static org.mockito.Mockito.*;





@ExtendWith(MockitoExtension.class)
class FacilityControllerTest {

    @Mock
    private FacilityServiceImpl facilityService;

    @Mock
    private IUserServiceImpl IUserService;

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

    Facility facility;
    Facility facility1;
    Facility facility2;
    Facility facility3;
    List<Facility> facilityList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        facility = new Facility(1L, "Cuidados", "Cuidados en casa", 12);
        facilityList.add(facility);
        mockMvc = MockMvcBuilders.standaloneSetup(facilityController).build();

    }

    @AfterEach
    void clear(){
        facility = null;
        facility1 = null;
        facility2 = null;
        facility3 = null;
    }

    @Test
    void findFacilityById() {
    }

    @Test
    @WithMockUser(username = "1", password = "pwd", roles = "ROLE_USER")
    void getFacility() throws Exception {
        when(facilityService.getAllFacilities()).thenReturn(facilityList);
        mockMvc.perform((MockMvcRequestBuilders.get("/api/facility/list")
                .contentType(asJsonString(facilityList))))
                .andDo(MockMvcResultHandlers.print());
        verify(facilityService).getAllFacilities();
        verify(facilityService,times(1)).getAllFacilities();
    }

    @Test
    void addFacility() {
    }

    @Test
    void editFacility() {
    }

    @Test
    void deleteFacilityById() {
    }
}