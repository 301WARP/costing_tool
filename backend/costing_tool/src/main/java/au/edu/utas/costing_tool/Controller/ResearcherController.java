package au.edu.utas.costing_tool.Controller;



// =============================================================================
// External Imports
// =============================================================================

import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Mapper.ResearcherDetailsMapper;
import au.edu.utas.costing_tool.Mapper.ResearcherListMapper;
import au.edu.utas.costing_tool.Mapper.UpdateContributionMapper;
import au.edu.utas.costing_tool.DTO.ContributionDetailsDTO;
import au.edu.utas.costing_tool.DTO.ResearcherListDTO;
import au.edu.utas.costing_tool.DTO.ResearcherRecommendationDTO;
import au.edu.utas.costing_tool.DTO.UpdateResearcherDTO;
import au.edu.utas.costing_tool.Enums.Title;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.ContributionID;
import au.edu.utas.costing_tool.Model.Project;
import au.edu.utas.costing_tool.Service.ContractService;
import au.edu.utas.costing_tool.Service.ContributionService;
import au.edu.utas.costing_tool.Service.ProjectService;


@RestController
@Data
public class ResearcherController
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ContributionService contributionService;

    @Autowired
    private final ContractService contractService;

    @Autowired
    private final ProjectService projectService;

    @Autowired
    private final ResearcherListMapper listMapper;

    @Autowired
    private final ResearcherDetailsMapper detailsMapper;
    /*
    private final ResearcherDetailsMapper detailsMapper
        = new ResearcherDetailsMapper();
    */

    @Autowired
    private final UpdateContributionMapper updateMapper;


    // =========================================================================
    // Methods
    // =========================================================================

    @CrossOrigin(origins="*")
    @GetMapping(path="/researchers/{projectID}")
    ResponseEntity<List<ResearcherListDTO>>
    fetchResearcherList(@PathVariable Long projectID)
    {
        if (projectID == null)
            return ResponseEntity.badRequest().body(null);

        if (this.projectService.findProject(projectID) == null)
            return ResponseEntity.notFound().build();

        List<ResearcherListDTO> researchers =
            this.contributionService.listAllContributionsforProject(projectID)
                .stream()
                .map(c -> this.listMapper.contributionToResearcherListDTO(c))
                .collect(Collectors.toList());
        
        if (researchers.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(researchers);
    }

    @CrossOrigin(origins="*")
    @GetMapping(path="/researchers/{projectID}/{contractID}")
    ResponseEntity<ContributionDetailsDTO>
    fetchResearcherDetails( @PathVariable Long projectID,
                            @PathVariable Long contractID)
    {
        if (projectID == null || contractID == null)
            return ResponseEntity.badRequest().build();

        ContributionID id = new ContributionID(contractID, projectID);

        Contribution contribution = this.contributionService
                                        .findContribution(id);

        if (contribution == null)
            return ResponseEntity.notFound().build();

        ContributionDetailsDTO detailsDTO =
                this.contributionService.contributionDetailsDTO(contribution);
        
        return ResponseEntity.ok().body(detailsDTO);
    }

    @CrossOrigin(origins="*")
    @PutMapping(path="/researchers/{projectID}/{contractID}")
    ResponseEntity<ContributionDetailsDTO>
    updateContribution( @RequestBody UpdateResearcherDTO dto,
                        @PathVariable Long projectID,
                        @PathVariable Long contractID)
    {
        // Bad request
        if (projectID == null || contractID == null || dto == null)
            return ResponseEntity.badRequest().build();

        ContributionID id = new ContributionID(contractID, projectID);

        Contribution oldContribution
            = this.contributionService.findContribution(id);

        // No such contribution
        if (oldContribution == null)
            return ResponseEntity.notFound().build();

        // TODO(Andrew): try?
        Contribution newContribution = this.updateMapper.updateResearcherDTOToContribution(dto);

        Contribution contribution
            = this  .contributionService
                    .updateContribution(oldContribution, newContribution);
        
        ContributionDetailsDTO detailsDTO
            = this.contributionService.contributionDetailsDTO(contribution);
        
        return ResponseEntity.ok().body(detailsDTO);
    }

    @CrossOrigin(origins="*")
    @PostMapping(path="/researchers/{projectID}/{contractID}")
    ResponseEntity<ContributionDetailsDTO>
    createContribution( @RequestBody UpdateResearcherDTO dto,
                        @PathVariable Long projectID,
                        @PathVariable Long contractID)
    {
        // Bad request
        if (projectID == null || contractID == null || dto == null)
            return ResponseEntity.badRequest().build();

        ContributionID id = new ContributionID(contractID, projectID);

        Contribution contribution = this.contributionService
                                        .findContribution(id);
        
        // Contribution already exists
        if (contribution != null)
        {
            /*
            Map<String, Long> pathVariables = new HashMap<String, Long>();
            pathVariables.put("projectID", projectID);
            pathVariables.put("contractID", contractID);
            */

            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                /*
                .path("/api/researchers/{projectID}/{contractID}")
                .buildAndExpand(pathVariables)
                */
                .build()
                .toUri();

            ContributionDetailsDTO detailsDTO
                = this  .contributionService
                        .contributionDetailsDTO(contribution);
            
            return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .location(location)
                .body(detailsDTO);
        }

        Contract contract = contractService.findContract(contractID);
        Project project = projectService.findProject(projectID);

        // No such contract/project
        if (contract == null || project == null)
            return ResponseEntity.notFound().build();

        //contribution = this.updateMapper.map(dto);
        contribution = this.updateMapper.updateResearcherDTOToContribution(dto);
        contribution.setContract(contract);
        contribution.setProject(project);

        contribution = this
            .contributionService
            .createContribution(contract, project, contribution);
        
        if (contribution == null)
            return ResponseEntity.internalServerError().build();

        ContributionDetailsDTO detailsDTO
            = this.contributionService.contributionDetailsDTO(contribution);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .build()
            .toUri();

        return ResponseEntity.created(location).body(detailsDTO);
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(path="/researchers/{projectID}/{contractID}")
    ResponseEntity<Void>
    deleteContribution( @PathVariable Long projectID,
                        @PathVariable Long contractID)
    {
        // Bad request
        if (projectID == null || contractID == null)
            return ResponseEntity.badRequest().build();

        Contribution contribution
            = contributionService.findContribution(
                new ContributionID(contractID, projectID)
            );

        // No such contribution
        if (contribution == null)
            return ResponseEntity.notFound().build();

        contributionService.deleteContribution(contribution);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins="*")
    @GetMapping(path="/researchers")
    ResponseEntity<List<ResearcherRecommendationDTO>>
    recommendResearchers(@RequestBody ResearcherRecommendationDTO dto)
    {
        // Bad request
        if (dto == null)
            return ResponseEntity.badRequest().build();
        
        List<ResearcherRecommendationDTO> recommendations
            = contributionService.recommendResearchers(dto);
        
        // No matches
        if (recommendations.isEmpty())
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok().body(recommendations);
    }
    
    @CrossOrigin(origins="*")
    @GetMapping(path="/researchers/{title}/{firstName}/{lastName}")
    ResponseEntity<List<ResearcherRecommendationDTO>>
    recommendResearchers(   @PathVariable Title title,
                            @PathVariable String firstName,
                            @PathVariable String lastName)
    {
        // Bad request
        if (title == null || firstName == null || lastName == null)
            return ResponseEntity.badRequest().build();
        
        List<ResearcherRecommendationDTO> recommendations
            = this  .contributionService
                    .recommendResearchers(title, firstName,lastName);
        
        // No matches
        if (recommendations.isEmpty())
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok().body(recommendations);
    }
}