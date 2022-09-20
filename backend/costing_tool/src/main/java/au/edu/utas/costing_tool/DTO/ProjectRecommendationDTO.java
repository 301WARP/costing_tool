package au.edu.utas.costing_tool.DTO;


// =============================================================================
// External Imports
// =============================================================================

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProjectRecommendationDTO
{
    Long id;
    String name;
}