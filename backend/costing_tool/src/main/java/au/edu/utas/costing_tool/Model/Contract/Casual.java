package au.edu.utas.costing_tool.Model.Contract;


// =============================================================================
// External Imports
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
// External Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.Contract.CasualClassification;
import au.edu.utas.costing_tool.Enums.Contract.CasualStaffType;
import au.edu.utas.costing_tool.Enums.Contract.PayCode;


@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("CASUAL")
@DiscriminatorOptions(force=true)
public class Casual extends Staff
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="`staff_type_casual`",
            insertable=false,
            updatable=false)
    @Enumerated(EnumType.STRING)
    protected CasualStaffType staffType;

    @Column(name="`classification_casual`",
            insertable=false,
            updatable=false)
    @Enumerated(value=EnumType.STRING)
    private CasualClassification classification;

    @Column(name="`pay_code`",
            insertable=false,
            updatable=false)
    @Enumerated(value=EnumType.STRING)
    private PayCode payCode;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="`staff_type_casual`",
                    referencedColumnName="`staff_type`"),
        @JoinColumn(name="`classification_casual`",
                    referencedColumnName="`classification`"),
        @JoinColumn(name="`pay_code`",
                    referencedColumnName="`pay_code`")
    })
    private CasualPaymentDetails paymentDetails;



    // =========================================================================
    // Methods
    // =========================================================================

    /*
    // TODO(Andrew): null checking and correct formula
    @Override
    public Double CostRate()
    {
        return this.getHourlyRate();
    }
    */
}