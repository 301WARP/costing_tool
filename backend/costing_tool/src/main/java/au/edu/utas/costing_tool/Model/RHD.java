package au.edu.utas.costing_tool.Model;

// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ContractType;
import au.edu.utas.costing_tool.Enums.RHDIncomeStream;



@Entity
@Table(name="Contract")
@DiscriminatorValue("RHD")
public class RHD extends Contract
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="classfication_rhd")
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
}
