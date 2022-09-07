package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.time.LocalDate;

import javax.persistence.Embeddable;


// TODO(Andrew): Consider making this its own table
@Embeddable
public class CIEndorsement
{
    // =========================================================================
    // Properties
    // =========================================================================

    private Boolean ciEndorsement;
    public Boolean getCIEndorsement() {return this.ciEndorsement;}
    public void setCIEndorsement(Boolean endorsed) {this.ciEndorsement = endorsed;}

    private LocalDate ciEndorsementDate;
    public LocalDate getCIEndorsementDate() {return this.ciEndorsementDate;}
    public void setCIEndorsementDate(LocalDate date) {this.ciEndorsementDate = date;}

    private Boolean riskAssessment;
    public Boolean getRiskAssessment() {return this.riskAssessment;}
    public void setRiskAssessment(Boolean undertaken) {this.riskAssessment = undertaken;}

    private Boolean risksManaged;
    public Boolean getRisksManaged() {return this.risksManaged;}
    public void setRisksManaged(Boolean satisfied) {this.risksManaged = satisfied;}

    private Boolean utasInsurance;
    public Boolean getUTASInsurance() {return this.utasInsurance;}
    public void setUTASInsurance(Boolean isCovered) {this.utasInsurance = isCovered;}

    private Boolean defenceStrategicGoods;
    public Boolean getDefenceStrategicGoods() {return this.defenceStrategicGoods;}
    public void setDefenceStrategicGoods(Boolean isOnList) {this.defenceStrategicGoods = isOnList;}

    private Boolean conflictOfInterest;
    public Boolean getConflictOfInterest() {return this.conflictOfInterest;}
    public void setConflictOfInterest(Boolean isPresent) {this.conflictOfInterest = isPresent;}

    private Boolean foreignPrincipals;
    public Boolean getForeignPrincipals() {return this.foreignPrincipals;}
    public void setForeignPrincipals(Boolean involves) {this.foreignPrincipals = involves;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public CIEndorsement() {}

    public CIEndorsement(   Boolean endorsement,
                            LocalDate date,
                            Boolean assessment,
                            Boolean satisfied,
                            Boolean isCovered,
                            Boolean isOnList,
                            Boolean isPresent,
                            Boolean involves)
    {
        this.setCIEndorsement(endorsement);
        this.setCIEndorsementDate(date);
        this.setRiskAssessment(assessment);
        this.setRisksManaged(satisfied);
        this.setUTASInsurance(isCovered);
        this.setDefenceStrategicGoods(isOnList);
        this.setConflictOfInterest(isPresent);
        this.setForeignPrincipals(involves);
    }
}