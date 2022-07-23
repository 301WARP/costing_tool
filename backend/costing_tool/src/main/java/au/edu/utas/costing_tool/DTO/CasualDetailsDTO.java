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

import au.edu.utas.costing_tool.Enums.CasualClassification;
import au.edu.utas.costing_tool.Enums.CasualStaffType;
import au.edu.utas.costing_tool.Enums.PayCode;



@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasualDetailsDTO extends ContributionDetailsDTO
{
    String staffType;
    String classification;
    Integer payCode;

    Double hourlyRate;
    Double wageAdjustment;
    Double salaryOnCostRate;

    // <year, hours>
    Map<Integer, Double> annualContributions;
}