package com.muratapps.expense_tracker_app.Mapper;

import com.muratapps.expense_tracker_app.Dto.CategoryDto;
import com.muratapps.expense_tracker_app.Dto.ExpenseDto;
import com.muratapps.expense_tracker_app.Entity.Category;
import com.muratapps.expense_tracker_app.Entity.Expense;

public class ExpenseMapper {


    public  Expense mapToExpense(ExpenseDto expenseDto){
        Category category=new Category();
        category.setId(expenseDto.categoryDto().id());

        return new Expense(
                expenseDto.id(),
                expenseDto.amount(),
                expenseDto.expenseDate(),
                category

        );

    }

    public  ExpenseDto mapToExpenseDto(Expense expense){


        return new ExpenseDto(
                expense.getId(),
                expense.getAmount()
                ,expense.getExpenseDate(),
                new CategoryDto(
                        expense.getCategory().getId(),
                        expense.getCategory().getName()
                )
        );
    }
}
