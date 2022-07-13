package au.edu.utas.costing_tool.Database;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Contract;


public interface ContractRepository extends JpaRepository<Contract, Long>
{

}