package au.edu.utas.costing_tool.DTO.Contribution;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasualDetailsDTO extends ContributionDetailsDTO
{
    String role;

    String staffType;
    String classification;
    Integer payCode;

    Double hourlyRate;
    Double wageAdjustment;
    Double salaryOnCostRate;

    // <year, hours>
    Map<Integer, Double> annualContributions;
}