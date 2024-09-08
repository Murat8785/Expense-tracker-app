package com.muratapps.expense_tracker_app.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDto(
       Long id,
 BigDecimal amount,
 LocalDate expenseDate,
 CategoryDto categoryDto
) {
}
