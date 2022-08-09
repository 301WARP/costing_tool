package au.edu.utas.costing_tool.Service;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.utas.costing_tool.DAO.ExpenseDAO;

// =============================================================================
// Package Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Expense;
//import au.edu.utas.costing_tool.Model.ContributionID;   All expenses?


@Service
public class ExpenseService
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ExpenseDAO expenseDAO;
    public ExpenseDAO expDAO() {return this.expenseDAO;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public ExpenseService(ExpenseDAO expenseDAO)
    {
        this.expenseDAO = expenseDAO;
    }
    

    // =========================================================================
    // Methods
    // =========================================================================

    public List<Expense> listAllExpenses()
    {
        return expDAO().readAll();
    }

    /*
        Will need to change for different expenses, e.g. AnnualExpenseID
    */
    public Expense findExpense(ExpenseID id)
    {
        return expDAO().readOne(id);
    }
}
