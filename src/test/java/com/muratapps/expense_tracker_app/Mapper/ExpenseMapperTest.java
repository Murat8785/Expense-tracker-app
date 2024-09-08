package com.muratapps.expense_tracker_app.Mapper;

import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import com.muratapps.expense_tracker_app.Dto.ExpenseDto;
import com.muratapps.expense_tracker_app.Entity.Category;
import com.muratapps.expense_tracker_app.Entity.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseMapperTest {

    private  ExpenseMapper mapper;

    @BeforeEach
    void setUp() {
        mapper=new ExpenseMapper();
    }

    @Test
    void mapToExpense() {
        ExpenseDto expenseDto=new ExpenseDto(
              1L, BigDecimal.valueOf(500), LocalDate.now(),new CategoryDto(1L,"test")
        );

        Expense expense=mapper.mapToExpense(expenseDto);

        assertEquals(expenseDto.id(),expense.getId());
        assertEquals(expenseDto.expenseDate(),expense.getExpenseDate());
        assertEquals(expenseDto.amount(),expense.getAmount());
        assertNotNull(expense.getCategory().getId());
        assertEquals(expenseDto.categoryDto().id(),expense.getCategory().getId());

    }

    @Test
    void mapToExpenseDto() {
         Expense expense=new Expense(1L, BigDecimal.valueOf(500), LocalDate.now(),new Category(1L,"Test"));


        ExpenseDto expenseDto=mapper.mapToExpenseDto(expense);

        assertEquals(expenseDto.id(),expense.getId());
        assertEquals(expenseDto.expenseDate(),expense.getExpenseDate());
        assertEquals(expenseDto.amount(),expense.getAmount());
        assertNotNull(expense.getCategory().getId());
        assertEquals(expenseDto.categoryDto().id(),expense.getCategory().getId());
    }







}