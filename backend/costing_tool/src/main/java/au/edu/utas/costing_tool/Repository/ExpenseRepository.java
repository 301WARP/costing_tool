package au.edu.utas.costing_tool.Repository;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Expense.Expense;


public interface ExpenseRepository extends JpaRepository<Expense, Long>
{
    @Query("SELECT e FROM Expense e WHERE project_id=:projectID")
    List<Expense> findAllByProject(Long projectID);
}