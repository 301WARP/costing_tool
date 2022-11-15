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
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRhdDTO extends UpdateResearcherDTO
{
    String role;
    Long contractId;

    String classification;
    String wageExpense;

    Double inKindPercent;
    Double onCostRate;
    Map<Integer, Double> annualContributions;
}