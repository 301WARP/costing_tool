package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.DiscriminatorOptions;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ContractType;
import au.edu.utas.costing_tool.Enums.RHDIncomeStream;


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
    public RHDIncomeStream getClassification() {return this.classification;}
    public void setClassification(RHDIncomeStream c) {this.classification = c;}

    @Column(name="salary")
    public Double annualSalary;
    public Double getAnnualSalary() {return this.annualSalary;}
    public void setAnnualSalary(Double salary) {this.annualSalary = salary;}

    // TODO(Andrew): Contribution?
    //public Dictionary<LocalDate, Double> FTE;


    // =========================================================================
    // Constructors
    // =========================================================================

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


    // =========================================================================
    // Methods
    // =========================================================================

    // TODO(Andrew): null checking and correct formula
    @Override
    public Double CostRate()
    {
        return this.getAnnualSalary();
    }
}