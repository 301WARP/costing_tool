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

import au.edu.utas.costing_tool.Enums.Contract.NonCasualClassification;
import au.edu.utas.costing_tool.Enums.Contract.NonCasualStaffType;
import au.edu.utas.costing_tool.Enums.Contract.Step;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="`non_casual_payment_details`")
@IdClass(value=NonCasualPaymentDetailsID.class)
public class NonCasualPaymentDetails
{
    @Id
    @Column(name="`staff_type`")
    @Enumerated(value=EnumType.STRING)
    private NonCasualStaffType staffType;

    @Id
    @Column(name="`classification`")
    @Enumerated(value=EnumType.STRING)
    private NonCasualClassification classification;

    @Id
    @Column(name="`step`")
    @Enumerated(value=EnumType.STRING)
    private Step step;

    @Column(name="`salary`")
    private Double salary;
}