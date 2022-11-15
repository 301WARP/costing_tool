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
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode(callSuper=true)
//@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateTbaDTO extends UpdateResearcherDTO
{
    String role;

    String contract;
    String staffType;
    String classification;
    String step;
    String payCode;

    Double wageExpense;

    Double inKindPercent;
    Double wageAdjustment;
    Double onCostRate;
    Map<Integer, Double> annualContributions;
}