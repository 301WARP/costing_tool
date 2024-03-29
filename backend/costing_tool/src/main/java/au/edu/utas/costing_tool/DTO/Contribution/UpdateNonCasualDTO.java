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
public class UpdateNonCasualDTO
{
    // Contribution
    String role;
    Double inKindPercent;
    Map<Integer, Double> annualContributions;
}