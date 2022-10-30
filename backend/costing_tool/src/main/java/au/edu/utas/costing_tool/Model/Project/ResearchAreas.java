package au.edu.utas.costing_tool.Model.Project;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// TODO(Andrew): Consider renaming to Activities in line with clearance form
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ResearchAreas
{
    private Double appliedResearch;
    private Double experimentalDevelopment;
    private Double strategicBasic;
    private Double pureBasic;
}