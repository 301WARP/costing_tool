package au.edu.utas.costing_tool.DTO;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ContractType;
import au.edu.utas.costing_tool.Enums.PayCode;
import au.edu.utas.costing_tool.Enums.RHDIncomeStream;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasualDetailsDTO
{
    // TODO(Andrew): A lot of crossover with ResearcherListDTO 
    //               Could assume they already have that and not send.
    Long staffID;
    String firstName;
    String lastName;
    String role;
    ContractType contract;

    RHDIncomeStream classification;
    PayCode payCode;

    Double wageExpense;
    Double inKindPercent;

    // <year, FTE>
    Map<Integer, Double> annualContributions;
}