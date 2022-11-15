package au.edu.utas.costing_tool.DTO.Contribution;


// =============================================================================
// External Imports
// =============================================================================

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NonCasualDetailsDTO extends ContributionDetailsDTO
{
    String role;

    String staffType;
    String classification;
    // TODO(Andrew): Could be a string?
    String step;

    Double startingSalary;
    Double wageAdjustment; 
    Double salaryOnCostRate;

    // <year, FTE>
    Map<Integer, Double> annualContributions;
}