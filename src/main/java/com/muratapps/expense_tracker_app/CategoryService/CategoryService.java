package com.muratapps.expense_tracker_app.CategoryService;

import com.muratapps.expense_tracker_app.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto findById(Long id);

    List<CategoryDto> findAll();

    CategoryDto updateCategory(CategoryDto categoryDto,Long id);


     void deleteCategory(Long id);


}
