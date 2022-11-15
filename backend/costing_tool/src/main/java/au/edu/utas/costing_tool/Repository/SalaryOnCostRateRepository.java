package au.edu.utas.costing_tool.Repository;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Contribution.SalaryOnCostRate;
import au.edu.utas.costing_tool.Model.Contribution.SalaryOnCostRateId;


@Repository
public interface SalaryOnCostRateRepository extends
    JpaRepository<SalaryOnCostRate, SalaryOnCostRateId>
{
}