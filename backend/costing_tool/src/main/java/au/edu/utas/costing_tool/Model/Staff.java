package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ContractType;


@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
public abstract class Staff extends Contract
{
    // =========================================================================
    // Properties
    // =========================================================================

    /*
    @Column(name="wage_adjustment")
    protected Double wageAdjustment;

    @Column(name="on_cost_rate")
    protected Double salaryOnCostRate;
    */


    // =========================================================================
    // Constructors
    // =========================================================================

    /*
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
    */
}