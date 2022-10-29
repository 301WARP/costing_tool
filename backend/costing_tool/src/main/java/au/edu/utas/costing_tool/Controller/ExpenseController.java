package au.edu.utas.costing_tool.Controller;


import java.net.URI;

// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Data;
import au.edu.utas.costing_tool.DTO.Expense.ExpenseDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.ExpenseListDTO;
import au.edu.utas.costing_tool.Mapper.ExpenseDetailsMapper;
import au.edu.utas.costing_tool.Mapper.ExpenseListMapper;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Expense.Expense;
import au.edu.utas.costing_tool.Model.Project.Project;
import au.edu.utas.costing_tool.Service.ExpenseService;
import au.edu.utas.costing_tool.Service.ProjectService;


@Data
@RestController
public class ExpenseController
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ExpenseService xService;

    @Autowired
    private final ProjectService pService;

    @Autowired
    private final ExpenseListMapper listMapper;

    @Autowired
    private final ExpenseDetailsMapper detailsMapper;


    // =========================================================================
    // Methods
    // =========================================================================

    @CrossOrigin(origins="*")
    @GetMapping(path="/expenses/{projectID}")
    public
    ResponseEntity<List<ExpenseListDTO>>
    fetchExpenseList(@PathVariable Long projectID)
    {
        if (projectID == null)
            return ResponseEntity.badRequest().build();

        List<Expense> expenses =
            this.xService
                .listAllExpensesForProject(projectID);
        
        if (expenses.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(this.listMapper.map(expenses));
    }


    @CrossOrigin(origins="*")
    @GetMapping(path="/expenses/details/{id}")
    public 
    ResponseEntity<ExpenseDetailsDTO>
    fetchExpenseDetails(@PathVariable Long id)
    {
        if (id == null)
            return ResponseEntity.badRequest().build();

        Expense expense = this.xService.findExpense(id);

        if (expense == null)
            return ResponseEntity.notFound().build();

        // TODO(Andrew): should all mappings work like this?
        ExpenseDetailsDTO details = null;
        try {
            details = this.detailsMapper.map(expense);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(details);
    }


    @CrossOrigin(origins="*")
    @PostMapping(path="/expenses/{projectID}")
    ResponseEntity<ExpenseDetailsDTO>
    createExpense(  @RequestBody ExpenseDetailsDTO dto,
                    @PathVariable Long projectID)
    {
        // Bad request
        if (projectID == null || dto == null)
            return ResponseEntity.badRequest().build();

        Project project = pService.findProject(projectID);

        // No such project
        if (project == null)
            return ResponseEntity.notFound().build();

        Expense expense = this.detailsMapper.map(dto);
        expense.setProject(project);
        expense = this  .xService
                        .createExpense(expense, expense.getClass());

        // Failed to create expense
        if (expense == null || expense.getProject() == null)
            return ResponseEntity.internalServerError().build();

        ExpenseDetailsDTO detailsDTO =
            this.detailsMapper.map(expense);

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/expenses/details/{expenseID}")
            .build(expense.getId());

        return ResponseEntity.created(location).body(detailsDTO);
    }

    @CrossOrigin(origins="*")
    @PutMapping(path="/expenses/{expenseID}")
    ResponseEntity<ExpenseDetailsDTO>
    updateExpense(  @RequestBody ExpenseDetailsDTO dto,
                    @PathVariable Long expenseID)
    {
        // Bad request
        if (expenseID == null || dto == null)
            return ResponseEntity.badRequest().build();

        Expense oldExpense
            = this.xService.findExpense(expenseID);

        // No such project/expense
        if (oldExpense == null || oldExpense.getProject() == null)
            return ResponseEntity.notFound().build();

        Expense newExpense = this.detailsMapper.map(dto);

        newExpense =
            this.xService
                .updateExpense(oldExpense, newExpense);
        
        ExpenseDetailsDTO detailsDTO =
            this.detailsMapper.map(newExpense);

        return ResponseEntity.ok().body(detailsDTO);
    }


    @CrossOrigin(origins="*")
    @DeleteMapping(path="/expenses/{expenseID}")
    ResponseEntity<Void>
    deleteExpense(@PathVariable Long expenseID)
    {
        // Bad request
        if (expenseID == null)
            return ResponseEntity.badRequest().build();

        Expense expense
            = this.xService.findExpense(expenseID);

        // No such project/expense
        if (expense == null)
            return ResponseEntity.notFound().build();

        this.xService
            .deleteExpense(expense);
        
        return ResponseEntity.ok().build();
    }
}