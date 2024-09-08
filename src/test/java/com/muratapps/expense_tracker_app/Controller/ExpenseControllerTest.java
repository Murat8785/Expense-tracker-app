package com.muratapps.expense_tracker_app.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.muratapps.expense_tracker_app.CategoryService.CategoryService;
import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import com.muratapps.expense_tracker_app.Dto.ExpenseDto;
import com.muratapps.expense_tracker_app.ExpenseService.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService;

    private ObjectMapper objectMapper;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ExpenseDto expenseDto=new ExpenseDto(1L, BigDecimal.valueOf(500), LocalDate.now(),new CategoryDto(1L,"test"));
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ExpenseController expenseController = new ExpenseController(expenseService);
        mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();

    }
    @Test
    void findById() throws Exception {

        ExpenseDto expenseDto = new ExpenseDto(1L, BigDecimal.valueOf(500), LocalDate.now(), new CategoryDto(1L, "test"));

        Mockito.when(expenseService.getExpenseById(expenseDto.id())).thenReturn(expenseDto);
        mockMvc.perform(get("/api/expenses/{id}",expenseDto.id()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(expenseDto)))
                .andExpect(status().isOk());


    }

    @Test
    void createExpense() throws Exception {

        ExpenseDto expenseDto = new ExpenseDto(1L, BigDecimal.valueOf(500), LocalDate.now(), new CategoryDto(1L, "test"));

        Mockito.when(expenseService.createExpense(expenseDto)).thenReturn(expenseDto);
        mockMvc.perform(post("/api/expenses/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expenseDto)))
                .andExpect(status().isCreated());

        Mockito.verify(expenseService, Mockito.times(1)).createExpense(expenseDto);

    }
    @Test
    void allExpenses() throws Exception{
        List<ExpenseDto> expenseDto = new ArrayList<>();
        expenseDto.add(new ExpenseDto(1L, BigDecimal.valueOf(500), LocalDate.now(), new CategoryDto(1L, "test")));
         Mockito.when(expenseService.getAllExpenses()).thenReturn(expenseDto);
         mockMvc.perform(get("/api/expenses/all").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(expenseDto))).andExpect(status().isOk());

         Mockito.verify(expenseService,Mockito.times(1)).getAllExpenses();

    }

    @Test
    void updateExp() {

    }

    @Test
    void deleteExp()  throws  Exception{
        ExpenseDto expenseDto = new ExpenseDto(1L, BigDecimal.valueOf(500), LocalDate.now(), new CategoryDto(1L, "test"));

        Mockito.doNothing().when(expenseService).deleteExpense(expenseDto.id());
        mockMvc.perform(delete("/api/expenses/delete/{id}",expenseDto.id()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(expenseDto))).andExpect(status().isOk());
    }
}