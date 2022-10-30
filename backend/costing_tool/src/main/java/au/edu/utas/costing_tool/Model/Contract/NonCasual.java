package au.edu.utas.costing_tool.Model.Contract;


// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.hibernate.annotations.DiscriminatorOptions;


// =============================================================================
// Project imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.Contract.NonCasualClassification;
import au.edu.utas.costing_tool.Enums.Contract.NonCasualStaffType;
import au.edu.utas.costing_tool.Enums.Contract.Step;


@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("NON_CASUAL")
@DiscriminatorOptions(force=true)
public class NonCasual extends Staff
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="staff_type_non_casual")
    @Enumerated(EnumType.STRING)
    protected NonCasualStaffType staffType;

    @Column(name="classification_non_casual")
    @Enumerated(EnumType.STRING)
    private NonCasualClassification classification;

    @Column(name="step")
    @Enumerated(EnumType.STRING)
    private Step step;

    @Column(name="salary")
    private Double startingSalary;
    

    // =========================================================================
    // Methods
    // =========================================================================

    /*
    // TODO(Andrew): null checking and correct formula
    @Override
    public Double CostRate()
    {
        return this.getStartingSalary();
    }
    */
}