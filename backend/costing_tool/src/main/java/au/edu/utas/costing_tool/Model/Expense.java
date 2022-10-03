package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ExpenseType;


// TODO(Andrew): Catch discriminators that don't apply to subclasses here
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name="expense")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="expense_type")
@DiscriminatorOptions(force=true)
public class Expense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Id
    @GeneratedValue
    @Column(name="id")
    protected Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="project_id")
    @JsonBackReference
    protected Project project;

    @Column(name="expense_type",
            insertable=false,
            updatable=false)
    @Enumerated(EnumType.STRING)
    protected ExpenseType type;

    @Column(name="cost_per_unit")
    protected Double costPerUnit;

    @Column(name="`in_kind_%`")
    protected double inKindPercent;

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="expense")
    @JsonManagedReference
    protected List<AnnualExpense> annualExpenses;


    // =========================================================================
    // Constructors
    // =========================================================================

    public Expense( Project project,
                    ExpenseType type,
                    Double costPerUnit,
                    Double inKindPercent)
    {
        this.setProject(project);
        this.setType(type);
        this.setCostPerUnit(costPerUnit);
        this.setInKindPercent(inKindPercent);

        // Update project with this expense
        if (project != null)
            this.getProject().addExpense(this);
    }


    // =========================================================================
    // Methods
    // =========================================================================

    public Boolean addAnnualExpense(AnnualExpense annualExpense)
    {
        return this.getAnnualExpenses().add(annualExpense);
    }

    public Boolean removeAnnualExpense(AnnualExpense annualExpense)
    {
        return this.getAnnualExpenses().remove(annualExpense);
    }

    public Double AnnualCost(Integer year)
    {
        // Assume there is at most one annual expense per year
        double units = this.getAnnualExpenses()
            .stream()
            .filter(ae -> year.equals(ae.getYear()))
            .mapToDouble(ae -> ae.getUnits())
            .findFirst()
            .orElse(0.0);
        
        Double costPerUnit = this.getCostPerUnit();

        if (costPerUnit == null)
            return null;

        return costPerUnit * units;
    }

    public Double AnnualCost(AnnualExpense expense)
    {
        if (expense == null)
            return null;

        Double units = expense.getUnits();
        Double costPerUnit = this.getCostPerUnit();

        if (units == null || costPerUnit == null)
            return null;

        return units * costPerUnit;
    }

    public Double Cost()
    {
        return this.getAnnualExpenses()
            .stream()
            .mapToDouble(this::AnnualCost)
            .reduce(0.0, (total, cost) -> total + cost);
    }
    
    public Double inKindDollar()
    {
        Double inKindPercent = this.getInKindPercent();
        Double cost = this.Cost();

        if (inKindPercent == null || cost == null)
            return null;

        return inKindPercent * cost;
    }
}