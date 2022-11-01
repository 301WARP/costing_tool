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

import au.edu.utas.costing_tool.Enums.Contract.CasualClassification;
import au.edu.utas.costing_tool.Enums.Contract.CasualStaffType;
import au.edu.utas.costing_tool.Enums.Contract.PayCode;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasualPaymentDetailsID implements Serializable
{
    private static final long serialVersionUID = 1L;

    CasualStaffType staffType;
    CasualClassification classification;
    PayCode payCode;
}