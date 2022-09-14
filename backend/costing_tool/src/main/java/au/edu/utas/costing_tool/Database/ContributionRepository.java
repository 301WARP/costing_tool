package au.edu.utas.costing_tool.Database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.ContributionID;

public interface ContributionRepository
    extends JpaRepository<Contribution, ContributionID>
{
    @Query("SELECT c FROM Contribution c WHERE project_id=:projectID")
    List<Contribution> findAllByProject(Long projectID);
}
