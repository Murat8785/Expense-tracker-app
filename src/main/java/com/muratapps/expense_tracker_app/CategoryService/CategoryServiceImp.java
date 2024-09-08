package com.muratapps.expense_tracker_app.CategoryService;

import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import com.muratapps.expense_tracker_app.Entity.Category;
import com.muratapps.expense_tracker_app.Mapper.CategoryMapper;
import com.muratapps.expense_tracker_app.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService{


   private CategoryRepository categoryRepository;

   private CategoryMapper categoryMapper;

    public CategoryServiceImp(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category= categoryMapper.MapToCategory(categoryDto);
        Category saveCategory=categoryRepository.save(category);

        return categoryMapper.MapToCategoryDto(saveCategory);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Böyle bir kullanıcı yok"));


        return categoryMapper.MapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> findAll() {

        List<Category> all = categoryRepository.findAll();

        return all.stream().map((category) -> categoryMapper.MapToCategoryDto(category)).collect(Collectors.toList());



    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Böyle bir kullanıcı yok"));

         category.setName(categoryDto.name());

         Category updateCategory=categoryRepository.save(category);

        return categoryMapper.MapToCategoryDto(updateCategory);
    }




    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Böyle bir kullanıcı yok"));


     categoryRepository.delete(category);



    }


}
