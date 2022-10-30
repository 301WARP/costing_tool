package au.edu.utas.costing_tool.Controller;


// =============================================================================
// External Imports
// =============================================================================

import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import au.edu.utas.costing_tool.DTO.Project.ProjectDetailsDTO;
import au.edu.utas.costing_tool.DTO.Project.ProjectListDTO;
import au.edu.utas.costing_tool.DTO.Project.ProjectRecommendationDTO;
import au.edu.utas.costing_tool.Mapper.ProjectDetailsMapper;
import au.edu.utas.costing_tool.Mapper.ProjectListMapper;
import au.edu.utas.costing_tool.Model.Project.Project;
import au.edu.utas.costing_tool.Service.ProjectService;


@RequiredArgsConstructor
@RestController
public class ProjectController
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ProjectService projectService;

    @Autowired
    private final ProjectListMapper listMapper;

    @Autowired
    private final ProjectDetailsMapper detailsMapper;


    // =========================================================================
    // Methods
    // =========================================================================

    @CrossOrigin(origins="*")
    @GetMapping(path="/projects")
    ResponseEntity<List<ProjectListDTO>>
    fetchProjectList()
    {
        List<ProjectListDTO> projects =
            this.projectService
                .listAllProjects()
                .stream()
                // TODO(Andrew): some level of authentication is required here
                //               to fetch information by userId
                .map(p -> this.listMapper.map(p, null))
                .collect(Collectors.toList());
        
        if (projects.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(projects);
    }


    @CrossOrigin(origins="*")
    @GetMapping(path="/projects/{id}")
    ResponseEntity<ProjectDetailsDTO>
    fetchProjectDetails(@PathVariable Long id)
    {
        if (id == null)
            return ResponseEntity.badRequest().build();

        Project project = this.projectService.findProject(id);

        if (project == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity
            .ok()
            .body(this.detailsMapper.map(project));
    }
    

    @CrossOrigin(origins="*")
    @GetMapping(path="/projects/find/{partialName}")
    ResponseEntity<List<ProjectRecommendationDTO>>
    recommendProjects(@PathVariable String partialName)
    {
        if (partialName == null)
            return ResponseEntity.badRequest().build();
        
        List<ProjectRecommendationDTO> recommendations = 
            this.projectService.recommend(partialName);
        
        if (recommendations == null || recommendations.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(recommendations);
   }


    @CrossOrigin(origins="*")
    @PostMapping(path="/projects")
    ResponseEntity<ProjectDetailsDTO>
    createProject(@RequestBody ProjectDetailsDTO dto)
    {
        if (dto == null)
            return ResponseEntity.badRequest().build();

        Project project = new Project();
        this.detailsMapper.map(dto, project);

        project = this.projectService.save(project);
    
        if (project.getId() == null)
            return ResponseEntity.internalServerError().build();
        
        ProjectDetailsDTO response = this.detailsMapper.map(project);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(project.getId())
            .toUri();

        return ResponseEntity.created(location).body(response);
    }


    @CrossOrigin(origins="*")
    @PutMapping(path="/projects/{id}")
    ResponseEntity<ProjectDetailsDTO>
    updateProject(  @PathVariable Long id,
                    @RequestBody ProjectDetailsDTO dto)
    {
        if (id == null || dto == null)
            return ResponseEntity.badRequest().build();

        Project project = this.projectService.findProject(id);
        
        this.detailsMapper.map(dto, project);

        project = this.projectService.save(project);
    
        if (project.getId() == null)
            return ResponseEntity.internalServerError().build();

        ProjectDetailsDTO response = this.detailsMapper.map(project);

        return ResponseEntity.ok().body(response);
    }
    

    @CrossOrigin(origins="*")
    @DeleteMapping(path="/projects/{id}")
    ResponseEntity<Void>
    deleteProject(@PathVariable Long id)
    {
        if (id == null)
            return ResponseEntity.badRequest().build();

        Project project = this.projectService.findProject(id);

        if (project == null)
            return ResponseEntity.notFound().build();
        
        this.projectService.deleteProject(project);

        return ResponseEntity.ok().build();
    }
}