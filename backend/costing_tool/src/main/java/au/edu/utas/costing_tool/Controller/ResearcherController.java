package au.edu.utas.costing_tool.Controller;


// =============================================================================
// External Imports
// =============================================================================
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Database.ResearcherRepository;
import au.edu.utas.costing_tool.Model.Researcher;


@RestController
public class ResearcherController
{
    // =========================================================================
    // Properties
    // =========================================================================
    @Autowired
    private final ResearcherRepository researcherRepository;
    private ResearcherRepository resRepos() {return researcherRepository;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearcherController(ResearcherRepository researcherRepos)
    {
        this.researcherRepository = researcherRepos;
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @GetMapping(path="/researchers")
    List<Researcher> all()
    {
        List<Researcher> researchers = new ArrayList<Researcher>();
        this.resRepos().findAll().forEach(researchers::add);
        return researchers;
    }


    @GetMapping(path="/researchers/{id}")
    Researcher one(@PathVariable Long id)
    {
        Optional<Researcher> researcher = this.resRepos().findById(id);

        // TODO(Andrew): return some sort of 404 message
        if (!researcher.isPresent())
            return null;

        return researcher.get();
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