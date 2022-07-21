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

import au.edu.utas.costing_tool.Enums.ContractType;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectListDTO
{
    Long id;
    String name;
    String role;
    ContractType type;
}
