package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Embeddable;


@Embeddable
public class ResearchAreas
{
    // =========================================================================
    // Properties
    // =========================================================================

    private Double appliedResearch;
    public Double getAppliedResearch() {return this.appliedResearch;}
    public void setAppliedResearch(Double percent) {this.appliedResearch = percent;}

    private Double experimentalDevelopment;
    public Double getExperimentalDevelopment() {return this.experimentalDevelopment;}
    public void setExperimentalDevelopment(Double percent) {this.experimentalDevelopment = percent;}

    private Double strategicBasic;
    public Double getStrategicBasic() {return this.strategicBasic;}
    public void setStrategicBasic(Double percent) {this.strategicBasic = percent;}

    private Double pureBasic;
    public Double getPureBasic() {return this.pureBasic;}
    public void setPureBasic(Double percent) {this.pureBasic = percent;}

    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearchAreas() {}
}
