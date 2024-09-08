package com.muratapps.expense_tracker_app.Controller;


import com.muratapps.expense_tracker_app.CategoryService.CategoryService;
import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id){
        CategoryDto category = categoryService.findById(id);

        return ResponseEntity.ok(category);


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,CategoryDto categoryDto){


        CategoryDto categoryDto1=categoryService.updateCategory(categoryDto, id);

     return ResponseEntity.ok(categoryDto1);

    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> findAll(){

        List<CategoryDto> categoryAll = categoryService.findAll();

        return ResponseEntity.ok(categoryAll);


    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){

      categoryService.deleteCategory(id);

    }

}
