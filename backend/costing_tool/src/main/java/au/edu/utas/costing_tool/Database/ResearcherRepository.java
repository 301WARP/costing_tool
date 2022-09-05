package au.edu.utas.costing_tool.Database;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import au.edu.utas.costing_tool.Enums.Title;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Researcher;


public interface ResearcherRepository extends JpaRepository<Researcher, Long>
{
    @Query("SELECT r FROM Researcher r "
            + "WHERE r.firstName LIKE %?1% "
            + "AND r.lastName LIKE %?2%")
    public
    List<Researcher>
    recommend(String firstName, String lastName);

    @Query("SELECT r FROM Researcher r "
            + "WHERE r.title = ?1 "
            + "AND r.firstName LIKE %?2% "
            + "AND r.lastName LIKE %?3%")
    public
    List<Researcher>
    recommend(Title title, String firstName, String lastName);
}