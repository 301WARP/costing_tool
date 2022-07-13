package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.Entity;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ContractType;


@Entity
public abstract class Staff extends Contract
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="wage_adjustment")
    protected Double wageAdjustment;
    public Double getWageAdjustment() {return this.wageAdjustment;}
    public void setWageAdjustment(Double adjustment)
        {this.wageAdjustment = adjustment;}

    @Column(name="on_cost_rate")
    protected Double salaryOnCostRate;
    public Double getSalaryOnCostRate() {return this.salaryOnCostRate;}
    public void setSalaryOnCostRate(Double rate) {this.salaryOnCostRate = rate;}


    // =========================================================================
    //Constructors
    // =========================================================================

    public Staff() {super();}

    public Staff(Researcher researcher, ContractType type)
    {
        super(researcher, type);
    }

    public Staff(   Researcher researcher,
                    ContractType type,
                    Double wageAdjustment,
                    Double salaryOnCostRate)
    {
        super(researcher, type);

        this.setWageAdjustment(wageAdjustment);
        this.setSalaryOnCostRate(salaryOnCostRate);
    }
}