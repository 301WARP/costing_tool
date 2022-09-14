package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.DiscriminatorOptions;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.CasualClassification;
import au.edu.utas.costing_tool.Enums.CasualStaffType;
import au.edu.utas.costing_tool.Enums.ContractType;
import au.edu.utas.costing_tool.Enums.PayCode;


@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue("CASUAL")
@DiscriminatorOptions(force=true)
public class Casual extends Staff
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="staff_type_casual")
    @Enumerated(EnumType.STRING)
    protected CasualStaffType staffType;

    @Column(name="classification_casual")
    @Enumerated(value=EnumType.STRING)
    private CasualClassification classification;

    @Column(name="pay_code")
    @Enumerated(value=EnumType.ORDINAL)
    private PayCode payCode;

    @Column(name="hourly_rate")
    private Double hourlyRate;

    // TODO(Andrew): Contribution?
    //private Dictionary<LocalDate, Double> Hours;


    // =========================================================================
    // Constructors
    // =========================================================================

    public Casual() {super();}

    public Casual(Researcher researcher)
    {
        super(researcher, ContractType.CASUAL);
    }

    public Casual(  Researcher researcher,
                    Double wageAdjustment,
                    Double salaryOnCostRate,
                    CasualClassification classification,
                    PayCode step,
                    Double hourlyRate)
    {
        super(researcher, ContractType.CASUAL, wageAdjustment, salaryOnCostRate);

        this.setClassification(classification);
        this.setPayCode(payCode);
        this.setHourlyRate(hourlyRate);
    }


    // =========================================================================
    // Methods
    // =========================================================================

    // TODO(Andrew): null checking and correct formula
    @Override
    public Double CostRate()
    {
        return this.getHourlyRate();
    }
}