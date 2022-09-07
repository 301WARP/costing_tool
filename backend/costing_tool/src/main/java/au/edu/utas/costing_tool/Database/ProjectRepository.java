package au.edu.utas.costing_tool.Database;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Project;


public interface ProjectRepository extends JpaRepository<Project, Long>
{
    //public boolean existsById(Long id);
}