package com.muratapps.expense_tracker_app.Mapper;

import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import com.muratapps.expense_tracker_app.Entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {


    @InjectMocks
    private CategoryMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CategoryMapper();

    }

    @Test
   public void mapToCategory() {

        CategoryDto categoryDto=new CategoryDto(
                1L,"TEST"
        );

        Category category=mapper.MapToCategory(categoryDto);

        Assertions.assertEquals(categoryDto.id(),category.getId());
        Assertions.assertEquals(categoryDto.name(),category.getName());

    }

    @Test
    void mapToCategoryDto() {

     Category category=new Category(
             1L,"Test"
     );

     CategoryDto categoryDto=mapper.MapToCategoryDto(category);

     assertEquals(category.getId(),categoryDto.id());
     assertEquals(category.getName(),categoryDto.name());



    }
}