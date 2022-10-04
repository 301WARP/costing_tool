package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.time.LocalDate;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// TODO(Andrew): Consider making this its own table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
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