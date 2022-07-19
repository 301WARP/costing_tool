package au.edu.utas.costing_tool.Database;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Researcher;


public interface ResearcherRepository extends JpaRepository<Researcher, Long>
{

}