package com.muratapps.expense_tracker_app.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.muratapps.expense_tracker_app.CategoryService.CategoryService;
import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void createCategory() throws Exception {

        CategoryDto categoryDto=new CategoryDto(1L,"test");
        Mockito.when(categoryService.createCategory(categoryDto)).thenReturn(categoryDto);
        mockMvc.perform(post("/api/categories/create").
                        contentType(MediaType.APPLICATION_JSON).
                        content(new ObjectMapper().writeValueAsString(categoryDto))).
                andExpect(status().isCreated());;

    }

    @Test
    void findById() throws Exception {
        CategoryDto categoryDto=new CategoryDto(1L,"test");

       Mockito.when(categoryService.findById(categoryDto.id())).thenReturn(categoryDto);
       mockMvc.perform(get("/api/categories/{id}",categoryDto.id()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(categoryDto)))
               .andExpect( status().isOk())
               .andExpect(jsonPath("$.name").value("test"));

      Mockito.verify(categoryService,Mockito.times(1)).findById(categoryDto.id());

    }


    @Test
    void testFindAll() throws Exception {

        List<CategoryDto> categoryDtoList=new ArrayList<>();
        categoryDtoList.add(new CategoryDto(1L,"test"));

        Mockito.when(categoryService.findAll()).thenReturn(categoryDtoList);
        mockMvc.perform(get("/api/categories/all").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(categoryDtoList)))
                .andExpect( status().isOk())
                .andExpect(jsonPath("$[0].name").value("test")); // list döndürürken exceptlemek istiyosak index numarasını vermeliyiz



    }


    @Test
    void testDelete()  throws  Exception{
        CategoryDto categoryDto=new CategoryDto(1L,"test");

       Mockito.doNothing().when(categoryService).deleteCategory(categoryDto.id());
        mockMvc.perform(delete("/api/categories/delete/{id}",categoryDto.id()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(categoryDto)))
                .andExpect( status().isOk());

    }
}