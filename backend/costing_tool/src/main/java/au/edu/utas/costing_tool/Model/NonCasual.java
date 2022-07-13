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
import au.edu.utas.costing_tool.Enums.NonCasualClassification;
import au.edu.utas.costing_tool.Enums.Step;


@Entity
@DiscriminatorValue("NON_CASUAL")
@DiscriminatorOptions(force=true)
public class NonCasual extends Staff
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="classification_non_casual")
    @Enumerated(EnumType.STRING)
    private NonCasualClassification classification;
    public NonCasualClassification getClassification()
        {return this.classification;}
    public void setClassification(NonCasualClassification classification)
        {this.classification = classification;}

    @Column(name="step")
    @Enumerated(EnumType.ORDINAL)
    private Step step;
    public Step getStep() {return this.step;}
    public void setStep(Step step) {this.step = step;}

    @Column(name="salary")
    private Double startingSalary;
    public Double getStartingSalary() {return this.startingSalary;}
    public void setStartingSalary(Double salary) {this.startingSalary = salary;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public NonCasual() {super();}

    public NonCasual(Researcher researcher)
    {
        super(researcher, ContractType.NON_CASUAL);
    }

    public NonCasual(   Researcher researcher,
                        Double wageAdjustment,
                        Double salaryOnCostRate,
                        NonCasualClassification classification,
                        Step step,
                        Double startingSalary)
    {
        super(  researcher,
                ContractType.NON_CASUAL,
                wageAdjustment,
                salaryOnCostRate);

        this.setClassification(classification);
        this.setStep(step);
        this.setStartingSalary(startingSalary);
    }
    

    // =========================================================================
    // Methods
    // =========================================================================

    // TODO(Andrew): null checking and correct formula
    @Override
    public Double CostRate()
    {
        return this.getStartingSalary();
    }
}