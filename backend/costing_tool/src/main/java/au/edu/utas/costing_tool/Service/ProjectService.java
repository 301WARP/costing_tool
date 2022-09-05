package au.edu.utas.costing_tool.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.utas.costing_tool.Database.ProjectRepository;
import au.edu.utas.costing_tool.Model.Project;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService
{
    @Autowired
    private final ProjectRepository projectRepos;

    public Project findProject(Long id)
    {
        if (id == null)
            return null;
        
        return this.projectRepos.findById(id).orElse(null);
    }

    public boolean exists(Long id)
    {
        if (id == null)
            return false;
        
        return this.projectRepos.existsById(id);
    }
}