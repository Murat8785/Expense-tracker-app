package com.muratapps.expense_tracker_app.ExpenseService;

import com.muratapps.expense_tracker_app.Dto.ExpenseDto;
import com.muratapps.expense_tracker_app.Entity.Category;
import com.muratapps.expense_tracker_app.Entity.Expense;
import com.muratapps.expense_tracker_app.Mapper.ExpenseMapper;
import com.muratapps.expense_tracker_app.Repository.CategoryRepository;
import com.muratapps.expense_tracker_app.Repository.ExpenseRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;

    private CategoryRepository categoryRepository;

    private ExpenseMapper expenseMapper;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {

        Expense expense = expenseMapper.mapToExpense(expenseDto);

        Expense savedExpense = expenseRepository.save(expense);

        return expenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + expenseId));


        return expenseMapper.mapToExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {

        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()
                .map((expense) -> expenseMapper.mapToExpenseDto(expense))
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + expenseId));
        expense.setAmount(expenseDto.amount());

        expense.setExpenseDate(expenseDto.expenseDate());
        if(expenseDto.categoryDto() != null){
            Category category = categoryRepository.findById(expenseDto.categoryDto().id())
                    .orElseThrow(() -> new RuntimeException("Category not found with id:" + expenseDto.categoryDto().id()));

            expense.setCategory(category);
        }

        Expense updatedExpense = expenseRepository.save(expense);
        return expenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + expenseId));

        expenseRepository.delete(expense);

    }
}

