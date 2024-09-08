package com.muratapps.expense_tracker_app.Mapper;

import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import com.muratapps.expense_tracker_app.Entity.Category;



public class CategoryMapper {

    public Category MapToCategory(CategoryDto categoryDto){
        return new Category(
                categoryDto.id(),
                categoryDto.name()

        );
    }


    public  CategoryDto MapToCategoryDto(Category category){
        return new CategoryDto(
                category.getId(),
                category.getName()

        );
    }


}
