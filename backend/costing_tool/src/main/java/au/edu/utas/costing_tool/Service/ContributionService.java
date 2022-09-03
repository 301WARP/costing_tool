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


// =============================================================================
// Package Imports
// =============================================================================

import au.edu.utas.costing_tool.Database.AnnualContributionRepository;
import au.edu.utas.costing_tool.Database.ContributionRepository;

import au.edu.utas.costing_tool.Model.AnnualContribution;
import au.edu.utas.costing_tool.Model.AnnualContributionID;
import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.ContributionID;

import au.edu.utas.costing_tool.Util.Log;


@Service
public class ContributionService
{
    // =========================================================================
    // Properties
    // =========================================================================

    /*
    @Autowired
    //private final ContributionDAO contributionDAO;
    private static final ContributionDAO contributionDAO
        = new ContributionDAO(new JPAEntityManagerFactory().getEntityManager());
    private static final ContributionDAO conDAO() {return contributionDAO;}
    */
    @Autowired
    private final ContributionRepository cRepos;
    //private final ContributionRepository contributionRepository() {return contributionRepository;}

    @Autowired
    private final AnnualContributionRepository acRepos;

    // =========================================================================
    // Constructors
    // =========================================================================

    /*
    public ContributionService(ContributionDAO contributionDAO)
    {
        this.contributionDAO = contributionDAO;
    }
    */
    
    public ContributionService( ContributionRepository cRepos,
                                AnnualContributionRepository acRepos)
    {
        this.cRepos = cRepos;
        this.acRepos = acRepos;
    }

    // =========================================================================
    // Methods
    // =========================================================================

    public Contribution createContribution(Contribution contribution)
    {
        /*
        //return conDAO().create(contribution);
        return con().save(contribution);
        */
        return null;
    }

    public List<Contribution> listAllContributions()
    {
        //return conDAO().readAll();
        return cRepos.findAll();
    }

    public Contribution findContribution(ContributionID id)
    {
        //return conDAO().readOne(id);
        return cRepos.findById(id).orElse(null);
    }

    @Transactional
    public Contribution updateContribution(Contribution old, Contribution nw)
    {
        //return conDAO().update(c);
        //return contributionRepository.save(c);

        if (old == null || nw == null)
            return null;
        
        //Log.log(old);
        //log(nw);

        old.setInKindPercent(nw.getInKindPercent());
        old.setRole(nw.getRole());

        this.updateAnnualContributions(old, nw);

        return old;
    }


    @Transactional
    private
    void
    updateAnnualContributions(Contribution old, Contribution nw)
    {
        // Remove annual contributions
        /*
        List<AnnualContribution> toRemove
            = acRepos.findAllById(
                ac  .stream()
                    .map(a -> a.getId())
                    .collect(Collectors.toList())
            )
            .stream()
            .filter(a -> c.findAnnualContribution(a) == null)
            .collect(Collectors.toList());
        */

        List<AnnualContribution> toRemove
            = old   .getAnnualContributions()
                    .stream()
                    //.peek(a -> this.log(a, "pre"))
                    .filter(a -> {
                        AnnualContributionID id
                            = new AnnualContributionID(
                                old.getContractID(),
                                old.getProjectID(),
                                a.getYear()
                            );
                        
                        return nw.findAnnualContributionByYear(a.getYear()) == null;
                    })
                    //.peek(a -> this.log(a, "post"))
                    .collect(Collectors.toList());

        Log.log(toRemove);

        toRemove.stream().forEach(a -> {
            old.removeAnnualContribution(a);
            acRepos.deleteOne(  a.getContractID(),
                                a.getProjectID(),
                                a.getYear());
        });
        //acRepos.deleteAllInBatch(toRemove);

        /*
        // create annual contributions
        List<AnnualContribution> toAdd
            = nw.getAnnualContributions()
                .stream()
                .filter(a -> old.findAnnualContribution(a) == null)
                .collect(Collectors.toList());
        this.log(toAdd);
        */

        /*
        toAdd.stream().forEach(a -> {
            old.addAnnualContribution(a);
        });
        */

        /*
        // update annual contributions
        Map<AnnualContribution, AnnualContribution> toUpdate
            = nw.getAnnualContributions()
                .stream()
                .filter(a -> old.findAnnualContribution(a) != null)
                .collect(Collectors.toMap(
                    Function.identity(),
                    a -> old.findAnnualContribution(a)
                ));
        this.log(toUpdate);
        */
        
        /*
        toUpdate.entrySet().forEach(e -> {
            AnnualContribution k = e.getKey();
            AnnualContribution v = e.getValue();
            if (v != null)
                v.setUnits(k.getUnits());
        });
        */
        /*
        Map<AnnualContribution, AnnualContribution> toUpdate
            = nw.getAnnualContributions()
                .stream()
                .map(a -> new Map.Entry<AnnualContribution, AnnualContribution>() {
                    
                }
                )
                .filter(a -> old.findAnnualContribution(a) != null)
                .collect(Collectors.toMap());

        toUpdate.stream().forEach(a -> {

            //a.setUnits();
        });
                */

    }

}