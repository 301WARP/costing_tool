package au.edu.utas.costing_tool.DTO;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ExpenseType;
import au.edu.utas.costing_tool.Enums.Facility;
import au.edu.utas.costing_tool.Enums.TimeUnit;


// TODO(Andrew): Add year/all-years, or create annual variant
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelDTO
{
    // TODO(Andrew): Should already be present in ExpenseListDTO
    //               Could assume the client already has this.
    Long id;
    ExpenseType type;
    String description;

    Facility facility;
    String facilityAndAuditCosts;

    Double costPerUnit;
    TimeUnit unitType;
    Double inKindPercent;

    // <year, units>
    Map<Integer, Double> annualUnits;
}