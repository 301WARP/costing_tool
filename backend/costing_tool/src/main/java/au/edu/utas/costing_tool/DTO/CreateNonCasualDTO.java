/*
package au.edu.utas.costing_tool.DTO;


// =============================================================================
// External Imports
// =============================================================================

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


// API endpoint: /api/researchers/{projectID}/{staffID}  PUT
// Exceptions:
//      Contribution already exists for Researcher

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNonCasualDTO extends CreateContributionDTO
{
    // Contract
    String contract;
    String classification;
    String staffType;
    String step;

    Double startingSalary;
    Double wageAdjustment;
    Double salaryOnCostRate;

    // Contribution
    String role;
    Double inKindPercent;
    Map<Integer, Double> annualContributions;
}
*/