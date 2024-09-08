package com.muratapps.expense_tracker_app.ExpenseService;

import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import com.muratapps.expense_tracker_app.Dto.ExpenseDto;
import com.muratapps.expense_tracker_app.Entity.Category;
import com.muratapps.expense_tracker_app.Entity.Expense;
import com.muratapps.expense_tracker_app.Mapper.ExpenseMapper;
import com.muratapps.expense_tracker_app.Repository.CategoryRepository;
import com.muratapps.expense_tracker_app.Repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ExpenseServiceImplTest {

    @Spy
    @InjectMocks
   private ExpenseServiceImpl expenseService;


    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ExpenseMapper expenseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExpense() {
        ExpenseDto expenseDto=new ExpenseDto(
                1L, BigDecimal.valueOf(500), LocalDate.now(),new CategoryDto(1L,"test")
        );

        Expense expense=new Expense(1L, BigDecimal.valueOf(500), LocalDate.now(),new Category(1L,"Test"));

        Expense saveExpense=new Expense(1L, BigDecimal.valueOf(500), LocalDate.now(),new Category(1L,"Test"));

        Mockito.when(expenseMapper.mapToExpense(expenseDto)).thenReturn(expense);
        Mockito.when(expenseRepository.save(expense)).thenReturn(saveExpense);
        Mockito.when(expenseMapper.mapToExpenseDto(saveExpense)).thenReturn(new ExpenseDto(
                1L, BigDecimal.valueOf(500), LocalDate.now(),new CategoryDto(1L,"test")
        ));

        ExpenseDto expense1=expenseService.createExpense(expenseDto);


        assertEquals(expense1.id(),expenseDto.id());
        assertEquals(expense1.expenseDate(),expenseDto.expenseDate());
        assertEquals(expense1.amount(),expenseDto.amount());
        assertEquals(expense1.categoryDto(),expenseDto.categoryDto());



        verify(expenseMapper,times(1)).mapToExpense(expenseDto);
        verify(expenseRepository,times(1)).save(expense);
        verify(expenseMapper,times(1)).mapToExpenseDto(saveExpense);



    }

    @Test
    void getExpenseById() {

        ExpenseDto expenseDto=new ExpenseDto(
                1L, BigDecimal.valueOf(500), LocalDate.now(),new CategoryDto(1L,"test")
        );

        Expense expense=new Expense(1L, BigDecimal.valueOf(500), LocalDate.now(),new Category(1L,"Test"));

        Mockito.when(expenseRepository.findById(expense.getId())).thenReturn(Optional.of(expense));
        Mockito.when(expenseMapper.mapToExpenseDto(expense)).thenReturn(new ExpenseDto(
                1L, BigDecimal.valueOf(500), LocalDate.now(),new CategoryDto(1L,"test")
        ));

        ExpenseDto expense1=expenseService.getExpenseById(expenseDto.id());

        assertEquals(expense1.id(),expenseDto.id());
        assertEquals(expense1.expenseDate(),expenseDto.expenseDate());
        assertEquals(expense1.amount(),expenseDto.amount());
        assertEquals(expense1.categoryDto(),expenseDto.categoryDto());

        verify(expenseRepository,times(1)).findById(expense.getId());
        verify(expenseMapper,times(1)).mapToExpenseDto(expense);



    }

    @Test
    void getAllExpenses() {
        List<Expense> expenses=new ArrayList<>();
        expenses.add(new Expense(1L, BigDecimal.valueOf(500), LocalDate.now(),new Category(1L,"Test")));

        Mockito.when(expenseRepository.findAll()).thenReturn(expenses);
        Mockito.when(expenseMapper.mapToExpenseDto(any(Expense.class))).thenReturn(new ExpenseDto(1L, BigDecimal.valueOf(500), LocalDate.now(),new CategoryDto(1L,"Test")));

        List<ExpenseDto> expenses1=expenseService.getAllExpenses();

        assertEquals(expenses1.size(),expenses.size());

        verify(expenseRepository,times(1)).findAll();
        verify(expenseMapper,times(1)).mapToExpenseDto(any(Expense.class));

    }


    @Test
    void updateExpense() {



        ExpenseDto expenseDto=new ExpenseDto(
                1L, BigDecimal.valueOf(500), LocalDate.now(),new CategoryDto(1L,"test")
        );



        Expense expense=new Expense(1L, BigDecimal.valueOf(500), LocalDate.now(),new Category(1L,"Test"));

        Expense saveExpense=new Expense(1L, BigDecimal.valueOf(500), LocalDate.now(),new Category(1L,"Test"));

        Mockito.when(expenseRepository.findById(expense.getId())).thenReturn(Optional.of(expense));
        Mockito.when(expenseRepository.save(any(Expense.class))).thenReturn(saveExpense);
        Mockito.when(expenseMapper.mapToExpenseDto(any(Expense.class))).thenReturn(new ExpenseDto(
                1L, BigDecimal.valueOf(500), LocalDate.now(),new CategoryDto(1L,"test")
        ));

        ExpenseDto expense1=expenseService.updateExpense(expense.getId(), expenseDto);


        assertEquals(expense1.id(),expenseDto.id());
        assertEquals(expense1.expenseDate(),expenseDto.expenseDate());
        assertEquals(expense1.amount(),expenseDto.amount());
        assertEquals(expense1.categoryDto(),expenseDto.categoryDto());

        verify(expenseRepository, times(1)).findById(expense.getId());
        verify(expenseMapper,times(1)).mapToExpense(expenseDto);
        verify(expenseRepository,times(1)).save(expense);
        verify(expenseMapper,times(1)).mapToExpenseDto(saveExpense);


    }

}