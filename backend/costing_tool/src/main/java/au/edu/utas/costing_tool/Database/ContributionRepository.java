package au.edu.utas.costing_tool.Database;

import org.springframework.data.jpa.repository.JpaRepository;

import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.ContributionID;

public interface ContributionRepository
    extends JpaRepository<Contribution, ContributionID>
{
    
}
