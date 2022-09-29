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

import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
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
    public Long getExpenseID() {return this.expenseID;}
    public void setExpenseID(Long id) {this.expenseID = id;}

    @Id
    @Column(name="year")
    protected Integer year;
    public Integer getYear() {return this.year;}
    public void setYear(Integer year) {this.year = year;}

    @Column(name="units")
    private Double units;
    public Double getUnits() {return this.units;}
    public void setUnits(Double units) {this.units = units;}

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId
    @JoinColumn(name="expense_id")
    @JsonBackReference
    protected Expense expense;
    public Expense getExpense() {return this.expense;}
    public void setExpense(Expense expense) {this.expense = expense;}


    // =========================================================================
    // Constructors
    // =========================================================================

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
}