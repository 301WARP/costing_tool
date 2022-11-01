package au.edu.utas.costing_tool.Model.Contract;


// =============================================================================
// External Imports
// =============================================================================

import java.io.Serializable;

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
public class NonCasualPaymentDetailsID implements Serializable
{
    private static final long serialVersionUID = 1L;

    NonCasualStaffType staffType;
    NonCasualClassification classification;
    Step step;
}