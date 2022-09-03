package au.edu.utas.costing_tool.Controller;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Mapper.ResearcherDetailsMapper;
import au.edu.utas.costing_tool.Mapper.ResearcherListMapper;
import au.edu.utas.costing_tool.Mapper.UpdateContributionMapper;
import au.edu.utas.costing_tool.DTO.CasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.ContributionDetailsDTO;
import au.edu.utas.costing_tool.DTO.NonCasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.RHDDetailsDTO;
import au.edu.utas.costing_tool.DTO.ResearcherListDTO;
import au.edu.utas.costing_tool.DTO.UpdateResearcherDTO;
import au.edu.utas.costing_tool.Database.AnnualContributionRepository;
import au.edu.utas.costing_tool.Database.ContractRepository;
import au.edu.utas.costing_tool.Database.ProjectRepository;
import au.edu.utas.costing_tool.Model.AnnualContribution;
import au.edu.utas.costing_tool.Model.Casual;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.ContributionID;
import au.edu.utas.costing_tool.Model.NonCasual;
import au.edu.utas.costing_tool.Model.Project;
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

    @Autowired
    private final ContractRepository contractRepository;

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final AnnualContributionRepository acRepository;

    private final ResearcherListMapper listMapper
        = new ResearcherListMapper();

    private final ResearcherDetailsMapper detailsMapper
        = new ResearcherDetailsMapper();

    private final UpdateContributionMapper updateMapper
        = new UpdateContributionMapper();

    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearcherController(ContributionService contributionService,
                                ContractRepository contractRepository,
                                ProjectRepository projectRepository,
                                AnnualContributionRepository acRepository)
    {
        this.contributionService = contributionService;
        this.contractRepository = contractRepository;
        this.projectRepository = projectRepository;
        this.acRepository = acRepository;
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @CrossOrigin(origins="*")
    @GetMapping(path="/researchers/{projectID}")
    List<ResearcherListDTO>
    fetchResearcherList(@PathVariable Long projectID)
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

    @CrossOrigin(origins="*")
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
            return detailsMapper.map(contribution, NonCasualDetailsDTO.class);
        if (contract instanceof Casual)
            return detailsMapper.map(contribution, CasualDetailsDTO.class);
        if (contract instanceof RHD)
            return detailsMapper.map(contribution, RHDDetailsDTO.class);
        
        return null;
    }

    @CrossOrigin(origins="*")
    @PutMapping(path="/researchers/{projectID}/{contractID}")
    Contribution
    updateContribution( @RequestBody UpdateResearcherDTO dto,
                        @PathVariable Long projectID,
                        @PathVariable Long contractID)
    {
        // Bad request
        if (projectID == null || contractID == null)
            return null;

        ContributionID id = new ContributionID(contractID, projectID);

        Contribution oldContribution = this.contributionService
                                        .findContribution(id);

        // No such contribution
        if (oldContribution == null)
            return null;

        Contribution newContribution = this.updateMapper.map(dto);

        return contributionService
            .updateContribution(oldContribution, newContribution);


        /*

        oldContribution.setRole(newContribution.getRole());
        oldContribution.setInKindPercent(newContribution.getInKindPercent());

        //oldContribution.getAnnualContributions().clear();
        //this.ClearAnnualContributions(oldContribution);

        for (AnnualContribution ac : newContribution.getAnnualContributions()) {
            List<AnnualContribution> acs = oldContribution.getAnnualContributions();
            Integer i = acs.indexOf(ac);
            if (i > -1) {
                AnnualContribution oldAc = acs.get(i);
                oldAc.setUnits(ac.getUnits());
                this.acRepository.saveAndFlush(oldAc);
            } else {
                oldContribution.addAnnualContribution(ac);
                this.acRepository.saveAndFlush(ac);
            }
        }
        

        //System.out.println(newContribution);

        this.contributionService.updateContribution(oldContribution);
        */




        /*
        for (AnnualContribution ac : contribution.getAnnualContributions()) {
            this.acRepository.delete(ac);
            contribution.removeAnnualContribution(ac);
        }
        */

        /*
        System.out.println(contribution.getAnnualContributions());
        AnnualContribution ac = contribution.getAnnualContributions().get(0);
        contribution.removeAnnualContribution(ac);
        this.acRepository.delete(ac);
        //this.acRepository.deleteAllInBatch(contribution.getAnnualContributions());
        //contribution.setAnnualContributions(null);
        this.acRepository.flush();
        */

        /*
        this.contributionService.updateContribution(contribution);

        Contract contract = contribution.getContract();

        // No such contract
        if (contract == null) return;

        this.updateMapper.map(dto, contribution);

        System.out.println(contribution);

        this.contributionService.updateContribution(contribution);
        */
    }

    @CrossOrigin(origins="*")
    @PostMapping(path="/researchers/{projectID}/{contractID}")
    void
    createContribution( @RequestBody UpdateResearcherDTO dto,
                        @PathVariable Long projectID,
                        @PathVariable Long contractID)
    {
        // Bad request
        if (projectID == null || contractID == null)
            return;

        ContributionID id = new ContributionID(contractID, projectID);

        Contribution contribution = this.contributionService
                                        .findContribution(id);

        // Contribution already exists
        if (contribution != null) return;

        Optional<Contract> c = contractRepository.findById(contractID);
        Optional<Project> p = projectRepository.findById(projectID);

        Contract contract;

        // Contract does not exist
        if (c.isEmpty() || (contract = c.get()) == null) return;

        // Project does not exist
        if (p.isEmpty() || p.get() == null) return;

        this.updateMapper.map(dto, contribution);

        this.contributionService.createContribution(contribution);

        contract.addContribution(contribution);

        //return contribution;
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(path="/researchers/{projectID}/{contractID}")
    void deleteContribution(@RequestBody ContributionDetailsDTO dto,
                            @PathVariable Long projectID,
                            @PathVariable Long contractID)
    {
    }

    @CrossOrigin(origins="*")
    @PutMapping(path="/researchers/{partialName}")
    List<String> recommendations(   @RequestBody ContributionDetailsDTO dto,
                            @PathVariable String partialName)
    {
        return null;
    }

    private void ClearAnnualContributions(Contribution contribution)
    {
        this.acRepository.deleteAllInBatch(contribution.getAnnualContributions());
        contribution.getAnnualContributions().clear();
        /*
        for (AnnualContribution ac : contribution.getAnnualContributions()) {
            ac.delete
        }
        */

    }
}