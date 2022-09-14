package au.edu.utas.costing_tool.Repository;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Contract;


public interface ContractRepository extends JpaRepository<Contract, Long>
{
    /*
    @Query("select case when count(c) > 0 then true else false end "
            + " from Contract c where id = ?1")
    public boolean existsById(Long id);
    */
}