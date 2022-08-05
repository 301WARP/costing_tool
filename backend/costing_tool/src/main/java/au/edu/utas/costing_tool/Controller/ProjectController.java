package au.edu.utas.costing_tool.Controller;


// =============================================================================
// External Imports
// =============================================================================

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Database.ProjectRepository;
import au.edu.utas.costing_tool.Model.Project;


@RestController
public class ProjectController
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ProjectRepository projectRepository;
    private ProjectRepository projRepos() {return this.projectRepository;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public ProjectController(ProjectRepository projectRepos)
    {
        this.projectRepository = projectRepos;
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @CrossOrigin(origins="*")
    @GetMapping(path="/projects")
    List<Project> all()
    {
        List<Project> projects = new ArrayList<Project>();
        this.projRepos().findAll().forEach(projects::add);
        return projects;
    }


    @CrossOrigin(origins="*")
    @GetMapping(path="/projects/{id}")
    Project one(@PathVariable Long id)
    {
        Optional<Project> project = this.projRepos().findById(id);

        // TODO(Andrew): return some sort of 404 message
        if (!project.isPresent())
            return null;

        return project.get();
    }
    /*
    public List<Project> loadProjects()
    {
        // TODO: not yet implemented
        return null;
    }

    public void loadProjectDetails(Project p)
    {
        // TODO: not yet implemented
    }

    public Project createProject()
    {
        // TODO: not yet implemented
        return null;
    }

    public void updateProject(Project p)
    {
        // TODO: not yet implemented
    }

    public List<Project> filterByOwner(Researcher r)
    {
        // TODO: not yet implemented
        return null;
    }

    public List<Project> filterByMember(Researcher r)
    {
        // TODO: not yet implemented
        return null;
    }

    public List<Project> filterByYear(LocalDate date)
    {
        // TODO: not yet implemented
        return null;
    }

    public List<Project> filterByName(String name)
    {
        // TODO: not yet implemented
        return null;
    }
    */
}