package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.UpdateResearcherDTO;

import au.edu.utas.costing_tool.Model.AnnualContribution;
import au.edu.utas.costing_tool.Model.Contribution;


@Mapper
public interface UpdateContributionMapper
{
    @Mapping(target="role", source="role")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="wageAdjustment", source="wageAdjustment")
    @Mapping(target="onCostRate", source="onCostRate")
    @Mapping(   target="annualContributions", source="annualContributions",
                qualifiedByName="mapAnnualContributions")
    @Mapping(target="contract", ignore=true)
    @Mapping(target="project", ignore=true)
    @Mapping(target="contractID", ignore=true)
    @Mapping(target="projectID", ignore=true)
    Contribution updateResearcherDTOToContribution(UpdateResearcherDTO dto);

    @Named("mapAnnualContributions")
    static
    List<AnnualContribution>
    mapAnnualContributions(Map<Integer,Double> acs)
    {
        if (acs == null)
            return null;
        
        return acs.entrySet().stream()
            .map(e -> new AnnualContribution(e.getKey(), e.getValue()))
            .collect(Collectors.toList());
    }
}