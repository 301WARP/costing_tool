package au.edu.utas.costing_tool.Mapper;

import org.modelmapper.ModelMapper;

import au.edu.utas.costing_tool.DTO.ResearcherListDTO;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.Researcher;


public class ResearcherListMapper extends ModelMapper
{

    public ResearcherListMapper()
    {
        super();
        /*
        this
            .typeMap(Researcher.class, ResearcherListDTO.class)
            .addMappings(mapper -> {
                mapper.map( Researcher::getStaffID,
                            ResearcherListDTO::setStaffID);

                mapper.map( src -> src.getTitle().name()
                                + " "
                                + src.getFirstName()
                                + " "
                                + src.getLastName(),
                            ResearcherListDTO::setName);

                mapper.map( src -> "",
                            ResearcherListDTO::setRole);
            });
            */
            /*
        this
            .typeMap(Contribution.class, ResearcherListDTO.class)
            .addMappings(mapper -> {
                mapper.map( c -> c.getContract().getResearcher().getStaffID(),
                            ResearcherListDTO::setStaffID);

                mapper.map( c -> c.getContract().getResearcher().getTitle().name()
                                + " "
                                + c.getContract().getResearcher().getFirstName()
                                + " "
                                + c.getContract().getResearcher().getLastName(),
                            ResearcherListDTO::setName);

                mapper.map( Contribution::getRole,
                            ResearcherListDTO::setRole);

                mapper.map( c -> c.getContract().getType().name(),
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
            */
    }

    public void init()
    {
        this
            .typeMap(Contribution.class, ResearcherListDTO.class)
            .addMappings(mapper -> {
                mapper.map( c -> c.getContract().getResearcher().getStaffID(),
                            ResearcherListDTO::setStaffID);

                mapper.map( c -> c.getContract().getResearcher().getTitle().name()
                                + " "
                                + c.getContract().getResearcher().getFirstName()
                                + " "
                                + c.getContract().getResearcher().getLastName(),
                            ResearcherListDTO::setName);

                mapper.map( Contribution::getRole,
                            ResearcherListDTO::setRole);

                mapper.map( c -> c.getContract().getType().name(),
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