package com.uniquecare.services;

import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import java.util.List;

public interface IFacilityService {
    List<Facility> getAllFacilities();
    Facility findFacilityById(Long id);
    Facility addNewFacility(Facility facility);
    List<Facility> getAllFacilitiesByCategoriesId(Long categoryId);
    List <Facility> getAllFacilitiesByCategoriesName(String categoryName);

    List <Facility> getAllFacilitiesByAssistantCity(String city);
    List <Facility> getAllFacilitiesByAssistantId(Long assistantId);
    void deleteFacilityById(Long id);
    Facility updateFacility(Facility facility);
    List<Facility> findAllByAssistant(User assistant);
}
