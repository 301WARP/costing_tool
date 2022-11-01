package au.edu.utas.costing_tool.Model.Contract;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.Contract.CasualClassification;
import au.edu.utas.costing_tool.Enums.Contract.CasualStaffType;
import au.edu.utas.costing_tool.Enums.Contract.PayCode;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="`casual_payment_details`")
@IdClass(value=CasualPaymentDetailsID.class)
public class CasualPaymentDetails
{
    @Id
    @Column(name="`staff_type`")
    @Enumerated(value=EnumType.STRING)
    private CasualStaffType staffType;

    @Id
    @Column(name="`classification`")
    @Enumerated(value=EnumType.STRING)
    private CasualClassification classification;

    @Id
    @Column(name="`pay_code`")
    @Enumerated(value=EnumType.STRING)
    private PayCode payCode;

    @Column(name="`hourly_rate`")
    private Double hourlyRate;
}