package au.edu.utas.costing_tool.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import au.edu.utas.costing_tool.DTO.Clearance.DisplayClearanceDTO;
import au.edu.utas.costing_tool.DTO.Clearance.UpdateClearanceDTO;
import au.edu.utas.costing_tool.Mapper.ClearanceMapper;
import au.edu.utas.costing_tool.Model.Project.Project;
import au.edu.utas.costing_tool.Service.ProjectService;
import lombok.Data;

@Data
@RestController
public class ClearanceController
{
    @Autowired
    private final ClearanceMapper clearanceMapper; 

    @Autowired
    private final ProjectService projectService; 


    @CrossOrigin(origins="*")
    @GetMapping(path="/clearance/{id}")
    public
    ResponseEntity<DisplayClearanceDTO>
    fetchClearanceForm(@PathVariable Long id)
    {
        if (id == null)
            return ResponseEntity.badRequest().build();

        Project project = this.projectService.findProject(id);

        if (project == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(
            this.clearanceMapper.map(project)
        );
    }
    

    // TODO(Andrew): Consider PATCH alternative
    @CrossOrigin(origins="*")
    @PutMapping(path="/clearance/{id}")
    public
    ResponseEntity<DisplayClearanceDTO>
    updateClearanceForm(@RequestBody UpdateClearanceDTO dto,
                        @PathVariable Long id)
    {
        if (id == null)
            return ResponseEntity.badRequest().build();

        Project project = this.projectService.findProject(id);

        if (project == null)
            return ResponseEntity.notFound().build();
        
        // TODO(Andrew): implement
        this.clearanceMapper.map(dto, project);
        project.setExternalResearchers(project.getExternalResearchersList());
        project = this.projectService.save(project);

        return ResponseEntity.ok(
            this.clearanceMapper.map(project)
        );
    }


    
}