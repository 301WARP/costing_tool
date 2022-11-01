package au.edu.utas.costing_tool.Model.Contract;


// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

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

    @Column(name="`staff_type_non_casual`",
            insertable=false,
            updatable=false)
    @Enumerated(EnumType.STRING)
    protected NonCasualStaffType staffType;

    @Column(name="`classification_non_casual`",
            insertable=false,
            updatable=false)
    @Enumerated(EnumType.STRING)
    private NonCasualClassification classification;

    @Column(name="`step`",
            insertable=false,
            updatable=false)
    @Enumerated(EnumType.STRING)
    private Step step;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="`staff_type_non_casual`",
                    referencedColumnName="`staff_type`"),
        @JoinColumn(name="`classification_non_casual`",
                    referencedColumnName="`classification`"),
        @JoinColumn(name="`step`",
                    referencedColumnName="`step`")
    })
    private NonCasualPaymentDetails paymentDetails;


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