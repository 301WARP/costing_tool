package au.edu.utas.costing_tool.Controller;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.Summary.SummaryDTO;
import au.edu.utas.costing_tool.Mapper.SummaryMapper;
import au.edu.utas.costing_tool.Model.Project.Project;
import au.edu.utas.costing_tool.Service.ProjectService;


@Data
@RestController
public class SummaryController
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ProjectService pService;

    @Autowired
    private final SummaryMapper mapper;


    // =========================================================================
    // Methods
    // =========================================================================

    @CrossOrigin(origins="*")
    @GetMapping(path="/summary/{projectID}")
    public
    ResponseEntity<SummaryDTO>
    fetchSummary(@PathVariable Long projectID)
    {
        if (projectID == null)
            return ResponseEntity.badRequest().build();

        Project project = this.pService.findProject(projectID);
        
        if (project == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(mapper.map(project));
    }
}