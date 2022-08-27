package com.uniquecare.repositories;

import com.uniquecare.models.Contract;
import com.uniquecare.models.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findFacilitiesByCategoriesId(Long categoryId);
    List<Facility> findFacilitiesByCategoriesName(String categoryName);
    List<Facility>findFacilitiesByAssistant(Long assistantId);
    Facility getFacilityById (Long facilityId);

}
