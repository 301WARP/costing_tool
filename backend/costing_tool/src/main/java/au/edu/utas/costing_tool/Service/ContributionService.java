package au.edu.utas.costing_tool.Service;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.Contribution.ContributionDetailsDTO;
import au.edu.utas.costing_tool.DTO.Researcher.ResearcherRecommendationDTO;

import au.edu.utas.costing_tool.Enums.Researcher.Title;

import au.edu.utas.costing_tool.Mapper.ResearcherDetailsMapper;
import au.edu.utas.costing_tool.Model.Contract.Casual;
import au.edu.utas.costing_tool.Model.Contract.CasualPaymentDetailsID;
import au.edu.utas.costing_tool.Model.Contract.Contract;
import au.edu.utas.costing_tool.Model.Contract.NonCasual;
import au.edu.utas.costing_tool.Model.Contract.NonCasualPaymentDetailsID;
import au.edu.utas.costing_tool.Model.Contract.RHD;
import au.edu.utas.costing_tool.Model.Contribution.AnnualContribution;
import au.edu.utas.costing_tool.Model.Contribution.Contribution;
import au.edu.utas.costing_tool.Model.Contribution.ContributionID;
import au.edu.utas.costing_tool.Model.Project.Project;
import au.edu.utas.costing_tool.Model.Researcher.Researcher;

import au.edu.utas.costing_tool.Repository.AnnualContributionRepository;
import au.edu.utas.costing_tool.Repository.CasualPaymentDetailsRepository;
import au.edu.utas.costing_tool.Repository.ContractRepository;
import au.edu.utas.costing_tool.Repository.ContributionRepository;
import au.edu.utas.costing_tool.Repository.NonCasualPaymentDetailsRepository;
import au.edu.utas.costing_tool.Repository.ResearcherRepository;


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

    @Autowired
    private final ContractRepository contractRepos;

    @Autowired
    private final NonCasualPaymentDetailsRepository ncpRepos;

    @Autowired
    private final CasualPaymentDetailsRepository cpRepos;

    @Autowired
    private final ResearcherDetailsMapper detailsMapper;


    private static final Pageable page = PageRequest.of(0, 10);


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

    public List<Contribution> listAllContributionsforProject(Long projectID)
    {
        return cRepos.findAllByProject(projectID);
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

        nw.setProject(old.getProject());

        nw  .getAnnualContributions()
            .forEach(ac -> ac.setContribution(nw));
        
        Contract nwContract =
            this.contractRepos
                .findById(nw.getContractID())
                .orElse(null);
        
        if (nwContract == null)
            return nw;
        
        Researcher researcher = nwContract.getResearcher();

        if (researcher == null)
            return nw;

        Long rid = researcher.getStaffID();

        if (rid == null)
            return nw;

        // Researcher ID 0 is reserved for TBA
        if (rid == 0) {
            nw.setContract(this.udpateTbaContract(nwContract));
        } else {
            nw.setContract(nwContract);
        }

        return cRepos.save(nw);
        /*
        old.setInKindPercent(nw.getInKindPercent());
        old.setRole(nw.getRole());
        old.setWageAdjustment(nw.getWageAdjustment());
        old.setOnCostRate(nw.getOnCostRate());

        // Update annual contributions
        this.remove(old, nw);
        this.add(old, nw);
        this.update(old, nw);

        return old;
        */
    }

    @Transactional
    public Contract udpateTbaContract(Contract contract)
    {
        if (contract instanceof NonCasual) {
            NonCasual c = (NonCasual) contract;

            NonCasualPaymentDetailsID pid =
                NonCasualPaymentDetailsID.builder()
                    .staffType(c.getStaffType())
                    .classification(c.getClassification())
                    .step(c.getStep())
                    .build();
            
            c.setPaymentDetails(
                this.ncpRepos
                    .findById(pid)
                    .orElse(null)
            );

            return c;
        }
        else if (contract instanceof Casual) {
            Casual c = (Casual) contract;

            CasualPaymentDetailsID pid =
                CasualPaymentDetailsID.builder()
                    .staffType(c.getStaffType())
                    .classification(c.getClassification())
                    .payCode(c.getPayCode())
                    .build();
            
            c.setPaymentDetails(
                this.cpRepos
                    .findById(pid)
                    .orElse(null)
            );

            return c;
        }
        else if (contract instanceof RHD) {
            return contract;
        } else {
            return null;
        }
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

        String fName = dto.getFirstName();
        String lName = dto.getLastName();
        Title title;

        String maybeTitle = dto.getTitle();

        if (maybeTitle == null) {
            title = null;
        }
        else {
            try {
                title = Title.valueOf(dto.getTitle());
            } catch (NullPointerException e) {
                title = null;
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        boolean titleAbsent = title == null || title == Title.NONE;
        boolean nameAbsent = fName == null && lName == null;

        if (titleAbsent && nameAbsent)
            researchers = rRepos.findAll(page).getContent();
        else if (titleAbsent)
            researchers = rRepos.recommend(fName, lName, page);
        else
            researchers = rRepos.recommend(title, fName, lName, page);
        
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
            researchers = this.rRepos.recommend( firstName, lastName, page);
        else
            researchers = this.rRepos.recommend( title, firstName, lastName, page);
        
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
        
        return this.detailsMapper.map(contribution);
    }

}