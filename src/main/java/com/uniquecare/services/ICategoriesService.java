package com.uniquecare.services;

import com.uniquecare.models.Categories;

import java.util.List;

public interface ICategoriesService {
    List<Categories> getAllCategories();
    Categories getById(Long id);
    Categories addNewCategory(Categories category);
    List <Categories> getAllFacilitiesByCategoriesName(String categoryName);
    List <Categories> getAllCategoriesByAssistantId(Long assistantId);
    void deleteCategoryById(Long id);
    Categories updateCategory(Categories category);
}
