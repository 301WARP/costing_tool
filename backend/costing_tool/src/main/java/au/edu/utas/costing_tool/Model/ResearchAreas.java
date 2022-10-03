package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;


// TODO(Andrew): Consider renaming to Activities in line with clearance form
@Data
@Builder
@Embeddable
public class ResearchAreas
{
    private Double appliedResearch;
    private Double experimentalDevelopment;
    private Double strategicBasic;
    private Double pureBasic;
}