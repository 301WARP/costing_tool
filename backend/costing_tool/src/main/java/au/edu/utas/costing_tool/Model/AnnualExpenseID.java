package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import java.io.Serializable;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Embeddable
@Table(name="annual_expense")
public class AnnualExpenseID implements Serializable
{
    // =========================================================================
    // Properties
    // =========================================================================

    // TODO(Andrew): Just use ids?
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="expense_id")
    @JsonBackReference
    protected Expense expense;
    public Expense getExpense() {return this.expense;}
    public void setExpense(Expense expense) {this.expense = expense;}

    @Column(name="year")
    protected Year year;
    public Year getYear() {return this.year;}
    public void setYear(Year year) {this.year = year;}

    // =========================================================================
    // Constructors
    // =========================================================================

    public AnnualExpenseID() {}

    public AnnualExpenseID(Expense expense, Year year)
    {
        this.setExpense(expense);
        this.setYear(year);
    }
}
