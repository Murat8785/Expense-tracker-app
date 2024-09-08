package com.muratapps.expense_tracker_app.CategoryService;

import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import com.muratapps.expense_tracker_app.Entity.Category;
import com.muratapps.expense_tracker_app.Mapper.CategoryMapper;
import com.muratapps.expense_tracker_app.Repository.CategoryRepository;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceImpTest {


  @InjectMocks
  private CategoryServiceImp categoryServiceImp;

  @Mock
  private CategoryRepository categoryRepository;


  @Mock
  private  CategoryMapper categoryMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCategory() {

        CategoryDto categoryDto=new CategoryDto(
                1L,"TEST"
        );

        Category category=new Category(
                1L,"Test"
        );

        Category savedcategory=new Category(
                1L,"Test"
        );

       Mockito.when(categoryMapper.MapToCategory(categoryDto)).thenReturn(category);

        Mockito.when(categoryRepository.save(category)).thenReturn(savedcategory);

        Mockito.when(categoryMapper.MapToCategoryDto(savedcategory)).thenReturn( new CategoryDto(1L,"TEST"));


        CategoryDto categoryDto1= categoryServiceImp.createCategory(categoryDto);

           assertEquals(categoryDto1.id(),categoryDto.id());
           assertEquals(categoryDto1.name(),categoryDto.name());

         verify(categoryMapper,times(1)).MapToCategory(categoryDto);

         verify(categoryRepository,times(1)).save(category);

         verify(categoryMapper,times(1)).MapToCategoryDto(savedcategory);


    }

    @Test
    void findById() {

        Category category=new Category(
                1L,"Test"
        );

        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(categoryMapper.MapToCategoryDto(any(Category.class))).thenReturn(new CategoryDto(1L,"Test"));


        CategoryDto categoryDto=categoryServiceImp.findById(category.getId());


        assertEquals(category.getId(),categoryDto.id());
        assertEquals(category.getName(),categoryDto.name());

        verify(categoryRepository,times(1)).findById(category.getId());








    }

    @Test
    void findAll() {

        List<Category> categories=new ArrayList<>();
        categories.add(new Category(
                1L,"Test"
        ));

        when(categoryRepository.findAll()).thenReturn(categories);
        when(categoryMapper.MapToCategoryDto(any(Category.class))).thenReturn(new CategoryDto(1L,"Test"));

        List<CategoryDto> ListDto=categoryServiceImp.findAll();


        assertEquals(categories.size(),ListDto.size());

        verify(categoryRepository,times(1)).findAll();




    }

    @Test
    void updateCategory() {

        Category category=new Category(
                1L,"Test"
        );
        CategoryDto categoryDto=new CategoryDto(
                1L,"TEST"
        );

        Category savedcategory=new Category(
                1L,"test"
        );





        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(savedcategory);
        when(categoryMapper.MapToCategoryDto(savedcategory)).thenReturn(new CategoryDto(
                1L,"TEST"
        ));

      CategoryDto categoryDto1 = categoryServiceImp.updateCategory(categoryDto, category.getId());


        assertEquals(categoryDto.name(),categoryDto1.name());
        assertEquals(categoryDto.id(),categoryDto1.id());

        verify(categoryRepository,times(1)).findById(category.getId());
        verify(categoryRepository,times(1)).save(category);
        verify(categoryMapper,times(1)).MapToCategoryDto(savedcategory);







    }
}