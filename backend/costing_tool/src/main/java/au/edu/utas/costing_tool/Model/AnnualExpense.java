package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.time.Year;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="annual_expense")
public class AnnualExpense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @EmbeddedId
    private AnnualExpenseID id;
    public AnnualExpenseID getID() {return this.id;}
    public void setID(AnnualExpenseID id) {this.id = id;}


    @Column(name="units")
    private Double units;
    public Double getUnits() {return this.units;}
    public void setUnits(Double units) {this.units = units;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public AnnualExpense() {}

    public AnnualExpense(   Expense expense,
                            Year year,
                            Double units)
    {
        AnnualExpenseID id = new AnnualExpenseID(expense, year);
        this.setID(id);
        this.setUnits(units);

        // TODO(Andrew): prevent duplicates
        expense.addAnnualExpense(this);
    }
}
