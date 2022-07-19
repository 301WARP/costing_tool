package au.edu.utas.costing_tool.Database;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.AnnualContribution;
import au.edu.utas.costing_tool.Model.AnnualContributionID;


public interface AnnualContributionRepository
    extends JpaRepository<AnnualContribution, AnnualContributionID>
{

}