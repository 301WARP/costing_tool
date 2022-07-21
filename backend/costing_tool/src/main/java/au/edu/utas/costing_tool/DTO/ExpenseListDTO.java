package au.edu.utas.costing_tool.DTO;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ExpenseType;


// TODO(Andrew): Add year/all-years, or create annual variant
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseListDTO
{
    Long id;
    ExpenseType type;
    String description;

    // TODO(Andrew): Could minimize this to actualCost + inKindPercent
    Double contractPrice;
    Double inKindPercent;
    Double inKindDollar;
    Double actualCost;
}