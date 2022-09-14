package au.edu.utas.costing_tool.Mapper;



// =============================================================================
// External Imports
// =============================================================================

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.ResearcherListDTO;
import au.edu.utas.costing_tool.Model.Contribution;


@Mapper
public interface ResearcherListMapper
{
    // Researcher
    @Mapping(target="staffID", source="contract.researcher.staffID")
    @Mapping(target="title", source="contract.researcher.title")
    @Mapping(target="firstName", source="contract.researcher.firstName")
    @Mapping(target="lastName", source="contract.researcher.lastName")
    // Contract
    @Mapping(target="contractID", source="contractID")
    @Mapping(target="contract", source="contract.contractType")
    @Mapping(target="role", source="role")
    // Contribution
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(   target="actualCost", source="contribution",
                qualifiedByName="priceToActualCost")
    ResearcherListDTO contributionToResearcherListDTO(Contribution contribution);

    @Named("priceToActualCost")
    static Double priceToActualCost(Contribution contribution)
    {
        return contribution.Price();
    }
}