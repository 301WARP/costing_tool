package au.edu.utas.costing_tool.Repository;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.edu.utas.costing_tool.Model.Expense.AnnualExpense;
import au.edu.utas.costing_tool.Model.Expense.AnnualExpenseID;


@Repository
public interface AnnualExpenseRepository
    extends JpaRepository<AnnualExpense, AnnualExpenseID>
{
}