package au.edu.utas.costing_tool.DTO.Expense;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
public class FacilityCostDetailsDTO extends ExpenseDetailsDTO
{
    Long id;
    String type;
    String description;
    Double inKindPercent;
    Double costPerUnit;

    String facilityCostName;
    String units;

    // <year, quantity>
    Map<Integer, Double> annualQuantity;
}