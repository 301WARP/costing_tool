package au.edu.utas.costing_tool.Controller;


// =============================================================================
// External Imports
// =============================================================================

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Expense;
import au.edu.utas.costing_tool.Database.ExpenseRepository;


@RestController
public class ExpenseController
{
    // =========================================================================
    // Properties
    // =========================================================================
    @Autowired
    private final ExpenseRepository expenseRepository;
    private ExpenseRepository expRepos() {return this.expenseRepository;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public ExpenseController(ExpenseRepository repos)
    {
        this.expenseRepository = repos;
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @GetMapping(path="/expenses")
    List<Expense> all()
    {
        List<Expense> expenses = new ArrayList<Expense>();
        this.expRepos().findAll().forEach(expenses::add);
        return expenses;
    }


    @GetMapping(path="/expenses/{id}")
    Expense one(@PathVariable Long id)
    {
        Optional<Expense> expense = this.expRepos().findById(id);

        // TODO(Andrew): return some sort of 404 message
        if (!expense.isPresent())
            return null;

        return expense.get();
    }



    /*
    public List<Expense> loadCosts()
    {
        // TODO: not yet implemented
        return null;
    }

    public void loadCostDetails(Expense c)
    {
        // TODO: not yet implemented
    }

    public void updateCost(Expense c)
    {
        // TODO: not yet implemented
    }

    public List<Expense> filterByType(ExpenseType type)
    {
        // TODO: not yet implemented
        return null;
    }

    public List<Expense> filterByDescription(String description)
    {
        // TODO: not yet implemented
        return null;
    }
    */
}