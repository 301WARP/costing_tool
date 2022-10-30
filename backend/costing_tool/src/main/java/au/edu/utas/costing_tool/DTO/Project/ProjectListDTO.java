package au.edu.utas.costing_tool.DTO.Project;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectListDTO
{
    Long id;
    String name;
    String startDate;
    String endDate;
    String role;
    String contractType;
}