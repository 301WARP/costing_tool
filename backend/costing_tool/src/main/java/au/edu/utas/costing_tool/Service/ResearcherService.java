package au.edu.utas.costing_tool.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.utas.costing_tool.Model.Researcher;
import au.edu.utas.costing_tool.Repository.ResearcherRepository;
import lombok.Data;

@Service
@Data
public class ResearcherService
{
    @Autowired
    private final ResearcherRepository researcherRepos;

    public Researcher findResearcher(Long staffID)
    {
        if (staffID == null)
            return null;
        
        return this.researcherRepos.findById(staffID).orElse(null);
    }

    public boolean existsById(Long staffID)
    {
        if (staffID == null)
            return false;
        
        return this.researcherRepos.existsById(staffID);
    }
}
