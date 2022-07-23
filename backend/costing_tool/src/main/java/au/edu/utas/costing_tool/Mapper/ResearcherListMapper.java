package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.ResearcherListDTO;
import au.edu.utas.costing_tool.Enums.ContractType;
import au.edu.utas.costing_tool.Enums.Title;
import au.edu.utas.costing_tool.Model.Contribution;


public class ResearcherListMapper extends ModelMapper
{
    // =========================================================================
    // Properties
    // =========================================================================

    /*
    Converter<Researcher, String> nameConverter
        = new AbstractConverter<Researcher,String>()
    {
        protected String convert(Researcher r)
        {
            return r.getTitle().name()
                + " "
                + r.getFirstName()
                + " "
                + r.getLastName();
        }
    };
    */

    Converter<Title, String> titleConverter
        = new AbstractConverter<Title,String>()
    {
        protected String convert(Title t)
        {
            return t.name();
        }
    };

    Converter<ContractType, String> contractConverter
        = new AbstractConverter<ContractType,String>()
    {
        protected String convert(ContractType c)
        {
            return c.name();
        }
    };


    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearcherListMapper()
    {
        super();

        this.typeMap(Contribution.class, ResearcherListDTO.class)
            .addMappings(mapper ->
        {
            mapper.map( c -> c.getContract().getResearcher().getStaffID(),
                        ResearcherListDTO::setStaffID);

                        /*
            mapper.using(this.nameConverter)
                    .map( c -> c.getContract().getResearcher(),
                        ResearcherListDTO::setName);
                        */

            mapper.using(this.titleConverter)
                    .map( c -> c.getContract().getResearcher().getTitle(),
                        ResearcherListDTO::setTitle);

            mapper.map( c -> c.getContract().getResearcher().getFirstName(),
                        ResearcherListDTO::setFirstName);

            mapper.map( c -> c.getContract().getResearcher().getLastName(),
                        ResearcherListDTO::setLastName);

            mapper.map( Contribution::getRole,
                        ResearcherListDTO::setRole);

            mapper.using(this.contractConverter)
                    .map( c -> c.getContract().getContractType(),
                        ResearcherListDTO::setContract);

            mapper.map( Contribution::cashIncome,
                        ResearcherListDTO::setCashIncome);

            mapper.map( Contribution::getInKindPercent,
                        ResearcherListDTO::setInKindPercent);

            mapper.map( Contribution::InKindDollar,
                        ResearcherListDTO::setInKindDollar);

            mapper.map( Contribution::Price,
                        ResearcherListDTO::setActualCost);
        });
    }
}