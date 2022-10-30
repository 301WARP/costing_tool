package au.edu.utas.costing_tool.DTO.Clearance;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisplayClearanceDTO
{
    // Project Details
    ProjectDetails projectDetails;

    // Lead Investigator
    Investigator leadInvestigator;
    // All other (UTAS) Investigators
    List<Investigator> investigators;
    // All other (external) Investigators
    List<Investigator> externalInvestigators;
    // Higher Degree Researhcer Students
    RhdStudents rhdStudents;

    // Research Codes
    ResearchCodes researchCodes;

    // Ethics/Biosafety/WHS/Clinical Trials
    Ethics ethics;

    // Project Costs
    ProjectCosts costs;

    // External Payments
    ExternalPayments externalPayments;

    /*
    // Chief Investigator Endorsement
    CiEndorsement ciEndorsement;

    // Director Endorsements
    List<DirectorEndorsement> directorEndorsements;

    // College Endorsements
    List<CollegeEndorsement> collegeEndorsements;
    */


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

    @Data
    @Builder
    public static class Investigator
    {
        // One of UTAS, non-UTAS, RHD
        //String type;

        String name;

        // Discipline/unit for UTAS-based investigators
        // Organisation for non-UTAS-based investigators
        String organisation;

        // UTAS investigators only
        Double fte;

        // RHD only
        // One of: UNPAID, CASUAL, SCHOLARSHIP
        //String involvement;
    }


    @Data
    @Builder
    public static class RhdStudents
    {
        List<String> names;

        String unit;

        // RHD only
        // One of: UNPAID, CASUAL, SCHOLARSHIP
        String involvement;
    }

    @Data
    @Builder
    public static class ResearchCodes
    {
        // Field of Research Codes
        Map<Integer, Double> forCodes;
        // Socio-Economic Objective Codes
        Map<Integer, Double> seoCodes;

        // Activities
        Double appliedResearch;
        Double experimentalDevelopment;
        Double strategicBasic;
        Double pureBasic;
    }

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

    @Data
    @Builder
    public static class ProjectCosts
    {
        // Amount requested from funding body
        Double fundingBodyTotal;

        // For projects not administered by UTAS
            // UTAS share of amount requeested from funding body
        Double utasShare;
            // UTAS indirect costs
        Double overheads;
            // Consultancy only
                // One of CI, INSTITUTE, ARA
        String paidTo;
                // Profit
        Double profit;

        // In-kind
        Map<String, Double> inKindUTAS;         // <discipline/unit, in-kind $>
        Map<String, Double> inKindPartner;      // <Partner, in-kind $>

        // Cash
        Map<String, Double> cashUTAS;           // <discipline/unit, cash $>
        Double cashDVCR;
        Map<String, Double> cashPartner;        // <discipline/unit, cash $>

        // Total cost of project
        Double actualCost;
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

    @Data
    @Builder
    public static class CollegeEndorsement
    {
        // College
        String college;

        // Endorsement
        String endorserName;
        Boolean endorsed;
        String endorsementDate;
    }
}