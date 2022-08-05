package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import org.modelmapper.ModelMapper;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.ResearcherListDTO;
import au.edu.utas.costing_tool.Model.Contribution;


public class ResearcherListMapper extends ModelMapper
{
    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearcherListMapper()
    {
        super();

        initResearcherListDTOMapper();
    }


    // =========================================================================
    // Methods
    // =========================================================================

    private void initResearcherListDTOMapper()
    {
        this.typeMap(Contribution.class, ResearcherListDTO.class)
            .addMappings(mapper ->
        {
            mapper.map( c -> c.getContract().getResearcher().getStaffID(),
                        ResearcherListDTO::setStaffID);

            mapper.using(EnumConverter.string)
                    .map( c -> c.getContract().getResearcher().getTitle(),
                        ResearcherListDTO::setTitle);

            mapper.map( c -> c.getContract().getResearcher().getFirstName(),
                        ResearcherListDTO::setFirstName);

            mapper.map( c -> c.getContract().getResearcher().getLastName(),
                        ResearcherListDTO::setLastName);

            mapper.map( Contribution::getContractID,
                        ResearcherListDTO::setContrtactID);

            mapper.using(EnumConverter.string)
                    .map( c -> c.getContract().getContractType(),
                        ResearcherListDTO::setContract);

            mapper.map( Contribution::getRole,
                        ResearcherListDTO::setRole);

            mapper.map( Contribution::Price,
                        ResearcherListDTO::setActualCost);

            mapper.map( Contribution::getInKindPercent,
                        ResearcherListDTO::setInKindPercent);
        });
    }
}