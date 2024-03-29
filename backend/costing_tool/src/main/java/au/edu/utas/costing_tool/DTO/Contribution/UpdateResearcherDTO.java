package au.edu.utas.costing_tool.DTO.Contribution;


// =============================================================================
// External Imports
// =============================================================================

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateResearcherDTO
{
    String role;
    Double inKindPercent;
    Double wageAdjustment;
    Double onCostRate;
    Map<Integer, Double> annualContributions;
}