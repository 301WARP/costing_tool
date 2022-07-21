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
import au.edu.utas.costing_tool.Enums.NonCasualClassification;
import au.edu.utas.costing_tool.Enums.NonCasualStaffType;
import au.edu.utas.costing_tool.Enums.Step;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NonCasualDetailsDTO
{
    // TODO(Andrew): A lot of crossover with ResearcherListDTO 
    //               Could assume they already have that and not send.
    Long staffID;
    String firstName;
    String lastName;
    String role;
    ContractType contract;

    NonCasualStaffType staffType;
    // TODO(Andrew): Specific to staffType
    NonCasualClassification classification;
    Step step;

    Double startingSalary;
    Double wageAdjustment; 
    // TODO(Andrew): salaryOnCost has constrained values
    Double SalaryOnCostRate;
    Double inKindPercent;

    // <year, FTE>
    Map<Integer, Double> annualContributions;
}