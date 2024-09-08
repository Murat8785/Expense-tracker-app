package com.muratapps.expense_tracker_app.Repository;

import com.muratapps.expense_tracker_app.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
