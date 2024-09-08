package com.muratapps.expense_tracker_app.Repository;

import com.muratapps.expense_tracker_app.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
