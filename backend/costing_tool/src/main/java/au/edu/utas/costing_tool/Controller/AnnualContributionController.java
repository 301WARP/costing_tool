/*
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

import au.edu.utas.costing_tool.Model.Contribution.AnnualContribution;
import au.edu.utas.costing_tool.Model.Contribution.AnnualContributionID;
import au.edu.utas.costing_tool.Repository.AnnualContributionRepository;


@RestController
public class AnnualContributionController
{
    // =========================================================================
    // Properties
    // =========================================================================
    @Autowired
    private final AnnualContributionRepository annualContributionRepository;
    private AnnualContributionRepository acRepos()
    {
        return this.annualContributionRepository;
    }


    // =========================================================================
    // Constructors
    // =========================================================================

    public AnnualContributionController(AnnualContributionRepository repos)
    {
        this.annualContributionRepository = repos;
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @GetMapping(path="/acs")
    List<AnnualContribution> all()
    {
        List<AnnualContribution> acs = new ArrayList<AnnualContribution>();
        this.acRepos().findAll().forEach(acs::add);
        return acs;
    }

    @GetMapping(path="/acs/{contractID}/{projectID}/{year}")
    AnnualContribution one( @PathVariable Long contractID,
                            @PathVariable Long projectID,
                            @PathVariable Integer year)
    {
        AnnualContributionID id
            = new AnnualContributionID(contractID, projectID, year);
        Optional<AnnualContribution> ac = this.acRepos().findById(id);

        // TODO(Andrew): return some sort of 404 message
        if (!ac.isPresent())
            return null;

        return ac.get();
    }
}
*/