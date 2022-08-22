package com.uniquecare.services;


import com.uniquecare.models.Categories;
import com.uniquecare.repositories.CategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CategoriesServiceImpl implements ICategoriesService {

    private final CategoriesRepository categoryRepository;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories getById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public Categories addNewCategory(Categories category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Categories> getAllFacilitiesByCategoriesName(String categoryName) {
        return null;
    }


    @Override
    public List<Categories> getAllCategoriesByAssistantId(Long assistantId) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Categories updateCategory(Categories category) {
        return categoryRepository.save(category);
    }
}

