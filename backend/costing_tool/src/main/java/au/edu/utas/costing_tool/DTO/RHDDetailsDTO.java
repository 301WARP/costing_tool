package au.edu.utas.costing_tool.DTO;


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
public class RHDDetailsDTO extends ContributionDetailsDTO
{
    String role;
    String classification;

    Double wageExpense;

    // <year, FTE>
    Map<Integer, Double> annualContributions;
}