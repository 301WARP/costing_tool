package au.edu.utas.costing_tool.DTO;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ProjectCategory;
import au.edu.utas.costing_tool.Enums.ResearchEntity;
import au.edu.utas.costing_tool.Enums.YearEndType;
import au.edu.utas.costing_tool.Enums.AMCMenzies;
import au.edu.utas.costing_tool.Enums.Category1Subtype;
import au.edu.utas.costing_tool.Enums.CrowdFunding;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDetailsDTO
{
    Long id;
    String name;
    String leadResearcherName;
    ProjectCategory category;
    AMCMenzies amcMenzies;
    Category1Subtype category1Subtype;
    Double profitMargin;

    LocalDate startDate;
    LocalDate endDate;
    YearEndType yearEnd;

    Double utasCashContribution;
    Double partnerCashContribution;
    CrowdFunding crowdFundingProvider;

    ResearchEntity entity;
    Map<Integer, Double> forCodes;
    Map<Integer, Double> seoCodes;
    Double appliedResearch;
    Double experimentalDevelopment;
    Double strategicBasic;
    Double pureBasic;
}