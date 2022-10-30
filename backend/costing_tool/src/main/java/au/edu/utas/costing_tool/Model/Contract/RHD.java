package au.edu.utas.costing_tool.Model.Contract;


// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.hibernate.annotations.DiscriminatorOptions;

import au.edu.utas.costing_tool.Enums.Contract.RHDIncomeStream;


@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("RHD")
@DiscriminatorOptions(force=true)
public class RHD extends Contract
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="classification_rhd")
    @Enumerated(value=EnumType.STRING)
    public RHDIncomeStream classification;

    @Column(name="salary")
    public Double annualSalary;


    // =========================================================================
    // Constructors
    // =========================================================================

    /*
    public RHD() {super();}

    public RHD(Researcher researcher)
    {
        super(researcher, ContractType.RHD);
    }

    public RHD( Researcher researcher,
                RHDIncomeStream classification,
                Double annualSalary)
    {
        super(researcher, ContractType.RHD);

        this.setClassification(classification);
        this.setAnnualSalary(annualSalary);
    }
    */


    // =========================================================================
    // Methods
    // =========================================================================

    /*
    // TODO(Andrew): null checking and correct formula
    @Override
    public Double CostRate()
    {
        return this.getAnnualSalary();
    }
    */
}