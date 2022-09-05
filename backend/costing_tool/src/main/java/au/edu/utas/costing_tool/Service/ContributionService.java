package au.edu.utas.costing_tool.Service;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.utas.costing_tool.DTO.CasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.ContributionDetailsDTO;
import au.edu.utas.costing_tool.DTO.NonCasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.RHDDetailsDTO;
import au.edu.utas.costing_tool.DTO.ResearcherRecommendationDTO;

// =============================================================================
// Package Imports
// =============================================================================

import au.edu.utas.costing_tool.Database.AnnualContributionRepository;
import au.edu.utas.costing_tool.Database.ContributionRepository;
import au.edu.utas.costing_tool.Database.ResearcherRepository;
import au.edu.utas.costing_tool.Enums.Title;
import au.edu.utas.costing_tool.Mapper.ResearcherDetailsMapper;
import au.edu.utas.costing_tool.Model.AnnualContribution;
import au.edu.utas.costing_tool.Model.Casual;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.ContributionID;
import au.edu.utas.costing_tool.Model.NonCasual;
import au.edu.utas.costing_tool.Model.Project;
import au.edu.utas.costing_tool.Model.RHD;
import au.edu.utas.costing_tool.Model.Researcher;
import lombok.Data;


@Service
@Data
public class ContributionService
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ContributionRepository cRepos;

    @Autowired
    private final AnnualContributionRepository acRepos;

    @Autowired
    private final ResearcherRepository rRepos;

    private final ResearcherDetailsMapper detailsMapper
        = new ResearcherDetailsMapper();


    // =========================================================================
    // Methods
    // =========================================================================

    public Contribution createContribution(Contribution contribution)
    {
        return null;
    }

    public List<Contribution> listAllContributions()
    {
        return cRepos.findAll();
    }

    public Contribution findContribution(ContributionID id)
    {
        return cRepos.findById(id).orElse(null);
    }

    @Transactional
    public Contribution updateContribution(Contribution old, Contribution nw)
    {
        if (old == null || nw == null)
            return null;

        old.setInKindPercent(nw.getInKindPercent());
        old.setRole(nw.getRole());

        // Update annual contributions
        this.remove(old, nw);
        this.add(old, nw);
        this.update(old, nw);

        return old;
    }

    @Transactional
    private void remove(Contribution old, Contribution nw)
    {
        List<AnnualContribution> toRemove
            = old   .getAnnualContributions()
                    .stream()
                    .filter(a -> !nw.hasAnnualContribution(a.getYear()))
                    .collect(Collectors.toList());

        toRemove.stream().forEach(a -> old.removeAnnualContribution(a));
        acRepos.deleteAllInBatch(toRemove);
    }

    @Transactional
    private void add(Contribution old, Contribution nw)
    {
        List<AnnualContribution> toAdd
            = nw.getAnnualContributions()
                .stream()
                .filter(a -> !old.hasAnnualContribution(a.getYear()))
                .collect(Collectors.toList());

        toAdd.stream().forEach(a -> old.addAnnualContribution(a));
    }

    @Transactional
    private void update(Contribution old, Contribution nw)
    {
        Map<AnnualContribution, AnnualContribution> toUpdate
            = nw.getAnnualContributions()
                .stream()
                .filter(a -> old.hasAnnualContribution(a.getYear()))
                .collect(Collectors.toMap(
                    Function.identity(),
                    a -> old.findAnnualContributionByYear(a.getYear())
                ));
        
        toUpdate.entrySet().forEach(e -> {
            AnnualContribution k = e.getKey();
            AnnualContribution v = e.getValue();
            if (v != null)
                v.setUnits(k.getUnits());
        });
    }

    @Transactional
    public
    Contribution
    createContribution( Contract contract,
                        Project project,
                        Contribution contribution)
    {
        return cRepos.save(contribution);
    }

    @Transactional
    public
    void
    deleteContribution(Contribution contribution)
    {
        cRepos.delete(contribution);
    }

    @Transactional
    public
    List<ResearcherRecommendationDTO>
    recommendResearchers(ResearcherRecommendationDTO dto)
    {
        List<Researcher> researchers;

        if (dto.getTitle() == null
            || dto.getTitle().equals("")
            || Title.valueOf(dto.getTitle()) == Title.NONE)
            researchers
                = rRepos.recommend( dto.getFirstName(),
                                    dto.getLastName());
        else
            researchers
                = rRepos.recommend( Title.valueOf(dto.getTitle()),
                                    dto.getFirstName(),
                                    dto.getLastName());
        
        return researchers
            .stream()
            .map(r -> ResearcherRecommendationDTO.builder()
                        .staffID(r.getStaffID())
                        .title(r.getTitle().toString())
                        .firstName(r.getFirstName())
                        .lastName(r.getLastName())
                        .build())
            .collect(Collectors.toList());
    }


    @Transactional
    public
    List<ResearcherRecommendationDTO>
    recommendResearchers(Title title, String firstName, String lastName)
    {
        List<Researcher> researchers;

        if (title.equals(Title.NONE) || title.equals(Title.TBA))
            researchers = this.rRepos.recommend( firstName, lastName);
        else
            researchers = this.rRepos.recommend( title, firstName, lastName);
        
        return researchers
            .stream()
            .map(r -> ResearcherRecommendationDTO.builder()
                        .staffID(r.getStaffID())
                        .title(r.getTitle().toString())
                        .firstName(r.getFirstName())
                        .lastName(r.getLastName())
                        .build())
            .collect(Collectors.toList());
    }

    public
    ContributionDetailsDTO
    contributionDetailsDTO(Contribution contribution)
    {
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
        else    // unkown researcher type
            return null;
    }
}