package au.edu.utas.costing_tool.DTO;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDetailsDTO
{
    Long id;
    String name;
    String description;

    Long leadResearcherId;
    String leadResearcherTitle;
    String leadResearcherFirstName;
    String leadResearcherLastName;

    String category;
    String category1Subtype;
    String amcMenzies;
    String amcNationalCentre;
    Double profitMargin;

    String startDate;
    String endDate;
    String yearEnd;

    Double utasCashContribution;
    Double partnerCashContribution;
    String crowdFundingProvider;

    String entity;
    Map<Integer, Double> forCodes;
    Map<Integer, Double> seoCodes;
    Double appliedResearch;
    Double experimentalDevelopment;
    Double strategicBasic;
    Double pureBasic;
}