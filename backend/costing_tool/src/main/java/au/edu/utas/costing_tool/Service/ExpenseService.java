package au.edu.utas.costing_tool.Service;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.utas.costing_tool.Mapper.ResearcherDetailsMapper;
import au.edu.utas.costing_tool.Model.Expense.Expense;
import au.edu.utas.costing_tool.Repository.ExpenseRepository;


@Service
@Data
public class ExpenseService
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ExpenseRepository xRepos;

    @Autowired
    private final ResearcherDetailsMapper detailsMapper;


    // =========================================================================
    // Methods
    // =========================================================================

    public
    List<Expense>
    listAllExpensesForProject(Long projectID)
    {
        return xRepos.findAllByProject(projectID);
    }

    public
    Expense
    findExpense(Long id)
    {
        return xRepos.findById(id).orElse(null);
    }

    @Transactional
    public
    <T extends Expense>
    Expense
    createExpense(Expense expense, Class<T> clazz)
    {
        Expense x;
        try {
            x = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        x = xRepos.save(x);

        expense.setId(x.getId());

        return xRepos.save(expense);
    }

    @Transactional
    public Expense updateExpense(Expense old, Expense nw)
    {
        nw.setProject(old.getProject());

        nw
            .getAnnualExpenses()
            .forEach(ax -> ax.setExpense(nw));

        return xRepos.save(nw);
    }

    @Transactional
    public
    void
    deleteExpense(Expense expense)
    {
        xRepos.delete(expense);
    }
}