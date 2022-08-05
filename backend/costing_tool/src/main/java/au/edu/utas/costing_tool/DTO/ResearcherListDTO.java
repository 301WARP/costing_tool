package au.edu.utas.costing_tool.DTO;


import java.util.Map;

import au.edu.utas.costing_tool.util.Pair;

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
    // Researcher
    Long staffID;
    String title;
    String firstName;
    String lastName;

    // Contract
    Long contrtactID;
    String contract;
    String role;
    // ContractID -> <contractType, Role>
    //Map<Long, Pair<String, String>>

    // Contribution
    Double inKindPercent;
    Double actualCost;
}