package com.uniquecare.services;

import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import com.uniquecare.repositories.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class FacilityServiceImpl implements IFacilityService {

    private final FacilityRepository facilityRepository;


    @Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility addNewFacility(Facility facility){
        return facilityRepository.save(facility);
    }

    @Override
    /** works*/
    public List<Facility> getAllFacilities () {
        return facilityRepository.findAll();
    }

    @Override
    public Facility findFacilityById (Long id){
        return facilityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Facility> getAllFacilitiesByCategoriesName (String categoryName){
        return facilityRepository.findFacilitiesByCategoriesName(categoryName);}

    @Override
    public List<Facility> getAllFacilitiesByAssistantId(Long assistantId) {
        return facilityRepository.findFacilitiesByAssistantId(assistantId);

    }

    @Override
    public List<Facility> getAllFacilitiesByCategoriesId (Long categoryId){
        return facilityRepository.findFacilitiesByCategoriesId(categoryId);}

    @Override
    public List<Facility> getAllFacilitiesByAssistantCity(String city) {
        return facilityRepository.findFacilitiesByAssistantCity(city);
    }

    /** works*/
    @Override
    public void deleteFacilityById (Long id){
        facilityRepository.deleteById(id);
    }

    /** works*/
    @Override
    public Facility updateFacility (Facility facility){
        return facilityRepository.save(facility);
    }

    @Override
    public List<Facility> findAllByAssistant(User assistant) {
        return facilityRepository.findAllByAssistant(assistant);
    }


}
