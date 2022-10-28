package au.edu.utas.costing_tool.DTO.Expense;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

import au.edu.utas.costing_tool.Enums.Expense.ExpenseType;


// Covers: Equipment, Consumables, External Contracting,
//         RHD Non-Stipend Costs, and Other expenses.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDTO
{
    // TODO(Andrew): Should already be present in ExpenseListDTO
    //               Could assume the client already has this.
    Long id;
    ExpenseType type;
    String description;

    Double costPerUnit;
    Double inKindPercent;

    // <year, units>
    Map<Integer, Double> annualUnits;
}