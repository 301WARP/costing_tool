package au.edu.utas.costing_tool.Model;

// =============================================================================
// Inbuilt imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ContractType;


@Entity
@Table(name="contract")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(   name="staff_type",
                        discriminatorType=DiscriminatorType.STRING)
public abstract class Staff extends Contract
{
    // =========================================================================
    // Properties
    // =========================================================================

    /*
    // TODO(Andrew): Unnecessary?
    @Column(name="staff_type")
    protected StaffType staffType;
    public StaffType getStaffType() {return this.staffType;}
    public void setStaffType(StaffType type) {this.staffType = type;}
    */

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
