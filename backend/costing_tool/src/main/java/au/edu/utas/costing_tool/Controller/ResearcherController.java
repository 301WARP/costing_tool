package au.edu.utas.costing_tool.Controller;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Mapper.ResearcherDetailsMapper;
import au.edu.utas.costing_tool.Mapper.ResearcherListMapper;

import au.edu.utas.costing_tool.DTO.CasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.ContributionDetailsDTO;
import au.edu.utas.costing_tool.DTO.NonCasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.RHDDetailsDTO;
import au.edu.utas.costing_tool.DTO.ResearcherListDTO;

import au.edu.utas.costing_tool.Model.Casual;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.ContributionID;
import au.edu.utas.costing_tool.Model.NonCasual;
import au.edu.utas.costing_tool.Model.RHD;

import au.edu.utas.costing_tool.Service.ContributionService;


@RestController
public class ResearcherController
{
    // =========================================================================
    // Properties
    // =========================================================================

    /*
    @Autowired
    private final ResearcherRepository researcherRepository;
    private ResearcherRepository resRepos() {return this.researcherRepository;}

    @Autowired
    private final ResearcherService researcherService;
    //private ResearcherService resService() {return this.researcherService;}
    */

    @Autowired
    private final ContributionService contributionService;
    //private ContributionService conService() {return this.contributionService;}

    private final ResearcherListMapper listMapper
        = new ResearcherListMapper();

    private final ResearcherDetailsMapper detailsMapper
        = new ResearcherDetailsMapper();


    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearcherController(ContributionService contributionService)
    {
        this.contributionService = contributionService;
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @GetMapping(path="/researchers/{projectID}")
    List<ResearcherListDTO> fetchResearcherList(@PathVariable Long projectID)
    {
        // TODO(Andrew): This needs a better error
        if (projectID == null) return null;

        // TODO(Andrew): I think this is horribly inefficient; the filtering
        //               should be done at the query level.
        return this.contributionService.listAllContributions()
            .stream()
            .filter(c -> c.getProjectID() == projectID)
            .map(c -> listMapper.map(c, ResearcherListDTO.class))
            .collect(Collectors.toList());
    }

    @GetMapping(path="/researchers/{projectID}/{contractID}")
    ContributionDetailsDTO
    fetchResearcherDetails( @PathVariable Long projectID,
                            @PathVariable Long contractID)
    {
        // TODO(Andrew): This needs a better error
        if (projectID == null || contractID == null)
            return null;

        ContributionID id = new ContributionID(contractID, projectID);

        Contribution contribution = this.contributionService
                                        .findContribution(id);

        // TODO(Andrew): return some sort of 404 message
        if (contribution == null) return null;

        Contract contract = contribution.getContract();

        // TODO(Andrew): return some sort of unkown contract message
        if (contract == null)
            return null;
        
        if (contract instanceof NonCasual)
        {
            System.out.println("non-casual");
            return detailsMapper.map(contribution, NonCasualDetailsDTO.class);
        }
        if (contract instanceof Casual)
        {
            System.out.println("casual");
            return detailsMapper.map(contribution, CasualDetailsDTO.class);
        }
        if (contract instanceof RHD)
        {
            System.out.println("rhd");
            return detailsMapper.map(contribution, RHDDetailsDTO.class);
        }

        
        System.out.println("none");
        return null;
    }

    /*
    public List<Researcher> loadResearchers()
    {
        // TODO: not yet implemented
        return null;
    }

    public void loadResearcherDetails(Researcher r)
    {
        // TODO: not yet implemented
    }

    public Researcher createResearcher()
    {
        // TODO: not yet implemented
        return null;
    }

    public void updateResearcher(Researcher r)
    {
        // TODO: not yet implemented
    }

    public List<Researcher> filterByYear(LocalDate date)
    {
        // TODO: not yet implemented
        return null;
    }

    public List<Researcher> filterByContract(ContractType type)
    {
        // TODO: not yet implemented
        return null;
    }

    public List<Researcher> filterByRole(String role)
    {
        // TODO: not yet implemented
        return null;
    }

    public List<Researcher> filterByName(String name)
    {
        // TODO: not yet implemented
        return null;
    }
    */
}