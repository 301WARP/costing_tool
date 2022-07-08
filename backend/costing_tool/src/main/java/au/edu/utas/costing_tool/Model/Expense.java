package au.edu.utas.costing_tool.Model;


import java.time.Year;

// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ExpenseType;


@Entity
@Table(name="expense")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="expense_type")
public class Expense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="id")
    protected Long ID;
    public Long getID() {return this.ID;}
    public void setID(Long type) {this.ID = type;}

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="project_id")
    @JsonBackReference
    protected Project project;
    public Project getProject() {return this.project;}
    public void setProject(Project project) {this.project = project;}

    @Column(name="expense_type")
    protected ExpenseType type;
    public ExpenseType getType() {return this.type;}
    public void setType(ExpenseType type) {this.type = type;}

    @Column(name="cost_per_unit")
    protected Double costPerUnit;
    public Double getCostPerUnit() {return this.costPerUnit;}
    public void setCostPerUnit(Double cost) {this.costPerUnit = cost;}

    @Column(name="in_kind_%")
    protected double inKindPercent;
    public Double getInKindPercent() {return this.inKindPercent;}
    public void setInKindPercent(Double inKindPercent)
        {this.inKindPercent = inKindPercent;}

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="expense")
    @JsonManagedReference
    protected List<AnnualExpense> annualExpenses;
    public List<AnnualExpense> getAnnualExpenses() {return this.annualExpenses;}
    public void annualExpenses(List<AnnualExpense> annualExpenses)
        {this.annualExpenses = annualExpenses;}

    // TODO(Andrew): How to do?
    //private Dictionary<LocalDate, Double> UnitsPerYear;


    // =========================================================================
    // Constructors
    // =========================================================================

    public Expense() {}

    public Expense( Project project,
                    ExpenseType type,
                    Double costPerUnit,
                    Double inKindPercent)
    {
        this.setProject(project);
        this.setType(type);
        this.setCostPerUnit(costPerUnit);
        this.setInKindPercent(inKindPercent);

        this.getProject().addExpense(this);
    }


    // =========================================================================
    // Methods
    // =========================================================================

    public double AnnualCost(Year year)
    {
        double units =  this.getAnnualExpenses()
            .stream()
            .filter(expense -> year.equals(expense.getID().getYear()))
            .mapToDouble(expense -> expense.getUnits())
            .findFirst()
            .orElse(0.0);

        if (this.getCostPerUnit() == null)
            return 0.0;

        return this.getCostPerUnit() * units;
    }

    protected double AnnualCost(AnnualExpense expense)
    {
        if (expense.getUnits() == null || this.getCostPerUnit() == null)
            return 0.0;

        return (double)expense.getUnits() * (double)this.getCostPerUnit();
    }

    public double Cost()
    {
        return this.annualExpenses
            .stream()
            .mapToDouble(this::AnnualCost)
            .reduce(0.0, (total, cost) -> total + cost);
    }
    
    public double inKindDollar()
    {
        if (this.getInKindPercent() == null)
            return 0.0;

        return (double)this.getInKindPercent() * this.Cost();
    }

    public boolean addAnnualExpense(AnnualExpense annualExpense)
    {
        return this.getAnnualExpenses().add(annualExpense);
    }

    public boolean removeAnnualExpense(AnnualExpense annualExpense)
    {
        return this.getAnnualExpenses().remove(annualExpense);
    }
}
