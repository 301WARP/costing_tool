package au.edu.utas.costing_tool.Repository;


import java.util.List;

// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Contract;


public interface ContractRepository extends JpaRepository<Contract, Long>
{
    @Query("SELECT c FROM Contract c WHERE c.researcher.staffID = ?1")
    public List<Contract> findForResearcher(Long staffID);
}