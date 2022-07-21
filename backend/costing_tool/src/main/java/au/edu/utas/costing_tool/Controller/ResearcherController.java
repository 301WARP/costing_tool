package au.edu.utas.costing_tool.Controller;


// =============================================================================
// External Imports
// =============================================================================

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Database.ResearcherRepository;
import au.edu.utas.costing_tool.Mapper.ResearcherListMapper;
import au.edu.utas.costing_tool.DTO.ResearcherListDTO;
import au.edu.utas.costing_tool.Model.Researcher;
import au.edu.utas.costing_tool.Service.ContributionService;
import au.edu.utas.costing_tool.Service.ResearcherService;


@RestController
public class ResearcherController
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ResearcherRepository researcherRepository;
    private ResearcherRepository resRepos() {return this.researcherRepository;}

    @Autowired
    private final ResearcherService researcherService;
    private ResearcherService resService() {return this.researcherService;}

    @Autowired
    private final ContributionService contributionService;
    private ContributionService conService() {return this.contributionService;}

    private final ResearcherListMapper mapper;


    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearcherController(ResearcherRepository researcherRepos,
                                ResearcherService researcherService,
                                ContributionService contributionService)
    {
        this.researcherRepository = researcherRepos;
        this.researcherService = researcherService;
        this.contributionService = contributionService;
        this.mapper = new ResearcherListMapper();
        this.mapper.init();
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @GetMapping(path="/researchers")
    List<ResearcherListDTO> fetchResearcherList()
    {
        //List<Researcher> researchers =  this.resService().listAllResearchers();

        /*
        return this.resService().listAllResearchers()
            .stream()
            .map(r -> mapper.map(r, ResearcherListDTO.class))
            .collect(Collectors.toList());
        */
        return this.conService().listAllContributions()
            .stream()
            .map(c -> mapper.map(c, ResearcherListDTO.class))
            .collect(Collectors.toList());
    }
    /*
    List<Researcher> fetchResearcherList()
    {
        List<Researcher> researchers = new ArrayList<Researcher>();
        this.resRepos().findAll().forEach(researchers::add);
        return researchers;
    }
    */


    @GetMapping(path="/researchers/{id}")
    Researcher fetchResearcherDetails(@PathVariable Long id)
    {
        Researcher researcher = this.resService().findResearcher(id);

        // TODO(Andrew): return some sort of 404 message
        if (researcher == null)
            return null;

        return researcher;
    }
    /*
    Researcher fetchResearcherDetails(@PathVariable Long id)
    {
        Optional<Researcher> researcher = this.resRepos().findById(id);

        // TODO(Andrew): return some sort of 404 message
        if (!researcher.isPresent())
            return null;

        return researcher.get();
    }
    */

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