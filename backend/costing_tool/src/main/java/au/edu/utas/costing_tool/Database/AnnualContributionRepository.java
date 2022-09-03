package au.edu.utas.costing_tool.Database;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.AnnualContribution;
import au.edu.utas.costing_tool.Model.AnnualContributionID;


@Repository
public interface AnnualContributionRepository
    extends JpaRepository<AnnualContribution, AnnualContributionID>
{
    @Query("DELETE FROM AnnualContribution ac WHERE ac.contractID=?1 AND ac.projectID=?2 AND year=?3")
    public void deleteOne(Long contractId, Long projectId, Integer year);

}