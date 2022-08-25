package com.uniquecare.controllers;


import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import com.uniquecare.Exceptions.ResourceNotFoundException;
import com.uniquecare.models.Categories;
import com.uniquecare.models.Facility;
import com.uniquecare.repositories.CategoriesRepository;
import com.uniquecare.repositories.FacilityRepository;
import com.uniquecare.services.CategoriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private final CategoriesRepository categoriesRepository;
    @Autowired
    private final CategoriesServiceImpl categoryService;

    public CategoryController(CategoriesRepository categoriesRepository, CategoriesServiceImpl categoryService, FacilityRepository facilityRepository) {
        this.categoriesRepository = categoriesRepository;
        this.categoryService = categoryService;
        this.facilityRepository = facilityRepository;
    }



    @GetMapping("/list")
    public ResponseEntity<List<Categories>> Category(Authentication authentication) {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/create")
    public Categories createNewEvent (@RequestBody @Valid Categories category){
        return categoryService.addNewCategory(category);
    }
    /*@PostMapping("/create")
    public ResponseEntity<Categories> saveCategory(@Valid @RequestBody Categories category) {
        Categories categorySaved = categoryService.addNewCategory(category);
        URI ubication = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categorySaved.getFacilities()).toUri();
        return ResponseEntity.created(ubication).body(categorySaved);
    }*/

    @GetMapping("/{id}")
    public Categories getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

  @GetMapping("/{categoryId}/facilities")
    public ResponseEntity<List<Facility>> getAllFacilitiesByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
        if (!categoriesRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Not found Tag  with id = " + categoryId);
        }
        List<Facility> facilities = facilityRepository.findFacilitiesByCategoriesId(categoryId);
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }

}

