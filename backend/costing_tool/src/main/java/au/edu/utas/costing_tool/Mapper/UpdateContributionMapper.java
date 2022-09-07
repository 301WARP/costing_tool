package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.UpdateResearcherDTO;

import au.edu.utas.costing_tool.Model.AnnualContribution;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.Project;


public class UpdateContributionMapper extends ModelMapper
{
    // =========================================================================
    // Constructors
    // =========================================================================

    public UpdateContributionMapper()
    {
        super();
        initMappings();
    }


    // =========================================================================
    // Methods
    // =========================================================================

    public void initMappings()
    {
        this.typeMap(UpdateResearcherDTO.class, Contribution.class)
            .addMappings(mapper ->
        {
            mapper.map( UpdateResearcherDTO::getRole,
                        Contribution::setRole);

            mapper.map( UpdateResearcherDTO::getInKindPercent,
                        Contribution::setInKindPercent);
        });
    }

    /*
    public
    Converter<Map<Integer, Double>, List<AnnualContribution>>
    makeAnnualContributionConverter(Contract contract, Project project)
    {
        return new AbstractConverter<Map<Integer, Double>, List<AnnualContribution>>() {

            protected List<AnnualContribution> convert(Map<Integer, Double> acMap)
            {
                return acMap.entrySet().stream()
                    .map(e -> new AnnualContribution(   project.getID(),
                                                        contract.getId(),
                                                        e.getKey(),
                                                        e.getValue()))
                    .collect(Collectors.toList());
            }
        };
    }
    */

    Converter<Map<Integer, Double>, List<AnnualContribution>> acConverter =
        new AbstractConverter<Map<Integer, Double>, List<AnnualContribution>>()
    {
        protected List<AnnualContribution> convert(Map<Integer, Double> acMap)
        {
            return acMap.entrySet().stream()
                .map(e -> new AnnualContribution(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        }
    };

    /*
    public Contribution map(UpdateResearcherDTO dto, Contribution contribution)
    {
        this
            .getTypeMap(UpdateResearcherDTO.class, Contribution.class)
            .addMappings(mapper ->
                mapper
                    .using( this.makeAnnualContributionConverter(contribution))
                    .map(   UpdateResearcherDTO::getAnnualContributions,
                            Contribution::setAnnualContributions)
                        );

        super.map(dto, contribution);

        return contribution;
    }
    */

    public Contribution map(UpdateResearcherDTO dto)
    {
        this
            .getTypeMap(UpdateResearcherDTO.class, Contribution.class)
            .addMappings(mapper ->
                mapper
                    .using( acConverter)
                    .map(   UpdateResearcherDTO::getAnnualContributions,
                            Contribution::setAnnualContributions)
                        );

        return super.map(dto, Contribution.class);
    }
}