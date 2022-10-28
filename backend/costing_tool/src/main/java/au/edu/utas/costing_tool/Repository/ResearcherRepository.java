package au.edu.utas.costing_tool.Repository;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import au.edu.utas.costing_tool.Enums.Researcher.Title;
import au.edu.utas.costing_tool.Model.Researcher.Researcher;


public interface ResearcherRepository extends JpaRepository<Researcher, Long>
{
    @Query("SELECT r FROM Researcher r "
            + "WHERE r.firstName LIKE %?1% "
            + "OR r.lastName LIKE %?2% "
            + "ORDER BY CASE "
            + "when (r.firstName LIKE %?1% AND r.lastName LIKE %?2%) then 1 "
                + "when r.lastName LIKE %?2% then 2 "
                + "when r.firstName LIKE %?1% then 3 "
            + "END ")
    public
    List<Researcher>
    recommend(String firstName, String lastName, Pageable pageable);

    @Query("SELECT r FROM Researcher r "
            + "WHERE r.title = ?1 "
            + "OR r.firstName LIKE %?2% "
            + "OR r.lastName LIKE %?3%"
            + "ORDER BY CASE "
            + "when (r.firstName LIKE %?2% AND r.lastName LIKE %?3%) then 1 "
                + "when r.lastName LIKE %?3% then 2 "
                + "when r.firstName LIKE %?2% then 3 "
                + "when r.title = ?1 then 4 "
            + "END ")
    public
    List<Researcher>
    recommend(Title title, String firstName, String lastName, Pageable pageable);

    @Query("SELECT r FROM Researcher r")
    public
    List<Researcher>
    recommend(Pageable pageable);
}