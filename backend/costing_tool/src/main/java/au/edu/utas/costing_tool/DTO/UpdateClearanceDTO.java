package au.edu.utas.costing_tool.DTO;

//import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateClearanceDTO
{
    // Project Details
    ProjectDetails projectDetails;

    // Ethics/Biosafety/WHS/Clinical Trials
    Ethics ethics;

    // Project Costs
    ProjectCosts costs;

    // External Payments
    ExternalPayments externalPayments;

    // Chief Investigator Endorsement
    //CiEndorsement ciEndorsement;

    // Director Endorsements
    //List<DirectorEndorsement> directorEndorsements;


    // =========================================================================
    // Nested classes
    // =========================================================================

    @Data
    @Builder
    public static class ProjectDetails
    {
        String category;
        String projectTitle;
        String description;
        String herdc;
        String startDate;
        String endDate;
        // TODO(Andrew): New entity?
        String fundingBody;
        String scheme;

        // Contract research and consultancies only
        String contactName;
        String contactEmail;
    }

    // TODO(Andrew): Requires authentication?
    @Data
    @Builder
    public static class Ethics
    {
        Boolean human;
        String humanRefNo;
        Boolean animal;
        String animalRefNo;
        Boolean controlledDrugs;
        Boolean clinicalTrial;
    }

    // TODO(Andrew): Consider adding DVCR section to Project Details page?
    @Data
    @Builder
    public static class ProjectCosts
    {
        Double cashDVCR;
    }

    // TODO(Andrew): not currently tracked
    // TODO(Andrew): new entity?
    @Data
    @Builder
    public static class ExternalPayments
    {
        Boolean thirdParties;

        // <party name, $ amount paid>
        Map<String, Double> parties;
    }

    /*
    // TODO(Andrew): requires authentication
    @Data
    @Builder
    public static class CiEndorsement
    {
        // Checklist
        Boolean riskAssessment;
        Boolean utasInsurance;
        Boolean dsgl;
        Boolean conflictOfInterest;
        Boolean foreignPrincipals;

        // Endorsement
        Boolean endorsed;
        String endorsementDate;
    }

    // TODO(Andrew): requires authentication
    @Data
    @Builder
    public static class DirectorEndorsement
    {
        // Checklist
        Boolean projectCosts;
        Boolean facilities;
        Boolean blessing;
        Boolean workload;
        Boolean ethics;
        Boolean conflictOfInterest;

        // UTAS Organisational Unit
        String unit;
        Double split;

        // Endorsement
        String endorserName;
        Boolean endorsed;
        String endorsementDate;
    }
    */
}