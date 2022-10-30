package au.edu.utas.costing_tool.Repository;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import au.edu.utas.costing_tool.Model.Project.Project;


public interface ProjectRepository extends JpaRepository<Project, Long>
{
    @Query("SELECT p FROM Project p WHERE p.name LIKE %?1% ")
    public List<Project> recommend(String partialName);
}