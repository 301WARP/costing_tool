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


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.RHDIncomeStream;


@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RHDDetailsDTO extends ContributionDetailsDTO
{
    String classification;

    Double wageExpense;

    // <year, FTE>
    Map<Integer, Double> annualContributions;
}