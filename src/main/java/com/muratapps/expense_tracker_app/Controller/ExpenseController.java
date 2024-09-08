package com.muratapps.expense_tracker_app.Controller;


import com.muratapps.expense_tracker_app.Dto.ExpenseDto;
import com.muratapps.expense_tracker_app.ExpenseService.ExpenseService;
import com.muratapps.expense_tracker_app.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {



    private  ExpenseService expenseService;


    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> findById(@PathVariable Long id){

        ExpenseDto expenseDto=expenseService.getExpenseById(id);

        return  ResponseEntity.ok(expenseDto);
   }

   @PostMapping("/create")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto){

        ExpenseDto expenseDto1=expenseService.createExpense(expenseDto);
        return new ResponseEntity<>(expenseDto1, HttpStatus.CREATED);



   }

   @GetMapping("/all")
    public ResponseEntity<List<ExpenseDto>> allExpenses(){
        List<ExpenseDto> expenseDto=expenseService.getAllExpenses();

        return ResponseEntity.ok(expenseDto);

   }

   @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExp(@PathVariable Long id,@RequestBody ExpenseDto expenseDto){

        ExpenseDto expenseUpd=expenseService.updateExpense(id,expenseDto);

        return ResponseEntity.ok(expenseUpd);

   }

    @DeleteMapping("/delete/{id}")
    public void deleteExp(@PathVariable Long id){

        expenseService.deleteExpense(id);

    }





}
