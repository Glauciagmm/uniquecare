package com.uniquecare.services;

import com.uniquecare.models.Categories;
import com.uniquecare.models.Facility;
import com.uniquecare.repositories.FacilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FacilityServiceImplTest {

    @Mock
    private FacilityRepository facilityRepository;

    private FacilityServiceImpl underTest;

    @BeforeEach
    void setUp(){
        underTest = new FacilityServiceImpl(facilityRepository);
    }

    @Test
    void addNewFacility() {
        Categories categories = new Categories(1L, "Cuidados");
        Set<Categories> categoriesList = new HashSet<>();
        categoriesList.add(categories);
        Facility facility = new Facility(
                "Limpieza",
                "Limpieza de casa y jardines",
                15,
                null,
                null
        );
        underTest.addNewFacility(facility);
        ArgumentCaptor<Facility> facilityArgumentCaptor = ArgumentCaptor.forClass(Facility.class);
        verify(facilityRepository).save(facilityArgumentCaptor.capture());
        Facility capturedFacility = facilityArgumentCaptor.getValue();
        assertThat(capturedFacility).isEqualTo(facility);
    }

    @Test
    void getAllFacilities() {
        underTest.getAllFacilities();
        verify(facilityRepository).findAll();
    }

    @Test
    void findFacilityById() {
        Facility facility = new Facility(
                1L
        );
        given(facilityRepository.findById(facility.getId())).willReturn(Optional.of(facility));
        underTest.findFacilityById(facility.getId());
        verify(facilityRepository).findById(facility.getId());
    }

    @Test
    void getAllFacilitiesByCategoriesName() {
    }

    @Test
    void getAllFacilitiesByAssistantId() {
    }

    @Test
    void getAllFacilitiesByCategoriesId() {
    }

    @Test
    void deleteFacilityById() {
        Facility facility = new Facility(
                1L

        );
        underTest.deleteFacilityById(1L);
        verify(facilityRepository).deleteById(1L);
    }

    @Test
    void updateFacility() {
        Categories categories = new Categories(1L, "Cuidados");
        Set<Categories> categoriesList = new HashSet<>();
        categoriesList.add(categories);
        Facility facility = new Facility(
                "Limpieza",
                "Limpieza de casa y jardines",
                15,
                null,
                null
        );
        underTest.updateFacility(facility);
        verify(facilityRepository).save(facility);
    }

}