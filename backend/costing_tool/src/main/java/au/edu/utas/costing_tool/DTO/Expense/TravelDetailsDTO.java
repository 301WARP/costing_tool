package au.edu.utas.costing_tool.DTO.Expense;


import lombok.AllArgsConstructor;
import lombok.Builder;

// =============================================================================
// External Imports
// =============================================================================

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
public class TravelDetailsDTO extends ExpenseDetailsDTO
{
    Long id;
    String type;
    String description;
    Double inKindPercent;

    Integer numTravellers;
    String departureDate;
    String returnDate;
    String returnFare;
    Double accommodation;
    Double carHire;
    Double meals;

    // XXX(Andrew): calculated from dates at front-end?
    // <year, units>
    //Map<Integer, Double> annualUnits;
}