package au.edu.utas.costing_tool.Model;

import java.time.LocalDate;

import javax.persistence.Embeddable;

@Embeddable
public class CIEndorsement
{
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

    private Boolean foreignPrinciples;
    public Boolean getForeignPrinciples() {return this.foreignPrinciples;}
    public void setForeignPrinciples(Boolean involves) {this.foreignPrinciples = involves;}

}
