package au.edu.utas.costing_tool.Service;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.utas.costing_tool.DAO.ContributionDAO;

// =============================================================================
// Package Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.ContributionID;


@Service
public class ContributionService
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ContributionDAO contributionDAO;
    public ContributionDAO conDAO() {return this.contributionDAO;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public ContributionService(ContributionDAO contributionDAO)
    {
        this.contributionDAO = contributionDAO;
    }
    

    // =========================================================================
    // Methods
    // =========================================================================

    public List<Contribution> listAllContributions()
    {
        return conDAO().readAll();
    }

    public Contribution findContribution(ContributionID id)
    {
        return conDAO().readOne(id);
    }
}