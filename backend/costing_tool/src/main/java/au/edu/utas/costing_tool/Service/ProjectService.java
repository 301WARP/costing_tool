package au.edu.utas.costing_tool.Service;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.ProjectRecommendationDTO;
import au.edu.utas.costing_tool.Model.Project;
import au.edu.utas.costing_tool.Model.Researcher;
import au.edu.utas.costing_tool.Repository.ProjectRepository;
import au.edu.utas.costing_tool.Repository.ResearcherRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProjectService
{
    // ========================================================================
    // Properties
    // ========================================================================

    @Autowired
    private final ProjectRepository projectRepos;

    @Autowired
    private final ResearcherRepository researcherRepos;


    // ========================================================================
    // Methods
    // ========================================================================

    public List<Project> listAllProjects()
    {
        return this.projectRepos.findAll();
    }

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
    
    public List<ProjectRecommendationDTO> recommend(String partialName)
    {
        if (partialName == null)
            return null;
        
        List<Project> projects = this.projectRepos.recommend(partialName);
        
        return projects
            .stream()
            .map(p -> ProjectRecommendationDTO
                        .builder()
                        .id(p.getId())
                        .name(p.getName())
                        .build())
            .collect(Collectors.toList());
    }
    
    @Transactional
    public Project save(Project project)
    {
        if (project == null)
            return null;
        
        return this.projectRepos.save(project);
    }

    @Transactional
    public void deleteProject(Project project)
    {
        if (project == null)
            return;
        
        // TODO(Andrew): delete oprhans?
        this.projectRepos.delete(project);
    }
    
    public Researcher findResearcher(Long staffId)
    {
        if (staffId == null)
            return null;
        
        return this.researcherRepos.findById(staffId).orElse(null);
    }
}