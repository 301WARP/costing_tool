package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="annual_expense")
@IdClass(value=AnnualExpenseID.class)
public class AnnualExpense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Id
    @Column(name="expense_id")
    protected Long expenseID;

    @Id
    @Column(name="year")
    protected Integer year;

    @Column(name="units")
    private Double units;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId
    @JoinColumn(name="expense_id")
    @JsonBackReference
    protected Expense expense;


    // =========================================================================
    // Constructors
    // =========================================================================

    /*
    public AnnualExpense(   Long expenseID,
                            Integer year,
                            Double units)
    {
        this.setExpenseID(expenseID);
        this.setYear(year);
        this.setUnits(units);

        // TODO(Andrew): prevent duplicates
        this.getExpense().addAnnualExpense(this);
    }
    */
}