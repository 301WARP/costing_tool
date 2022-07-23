package au.edu.utas.costing_tool.DTO;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// TODO(Andrew): Add year/all-years, or create annual variant
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResearcherListDTO
{
    Long staffID;
    String Title;
    String firstName;
    String lastName;
    String role;
    String contract;

    // TODO(Andrew): Could minimize this to actualCost + inKindPercent
    Double cashIncome;
    Double inKindPercent;
    Double inKindDollar;
    Double actualCost;
}