package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.time.LocalDate;

import javax.persistence.Embeddable;

import lombok.Data;


// TODO(Andrew): Consider making this its own table
@Data
@Embeddable
public class CIEndorsement
{
    private Boolean ciEndorsement;
    private LocalDate ciEndorsementDate;
    private Boolean riskAssessment;
    private Boolean utasInsurance;
    private Boolean defenceStrategicGoods;
    private Boolean conflictOfInterest;
    private Boolean foreignPrincipals;
}