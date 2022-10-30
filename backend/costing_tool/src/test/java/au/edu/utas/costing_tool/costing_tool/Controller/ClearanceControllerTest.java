package au.edu.utas.costing_tool.costing_tool.Controller;


// =============================================================================
// External Imports
// =============================================================================

import java.time.LocalDate;
import java.time.Month;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Controller.ClearanceController;
import au.edu.utas.costing_tool.DTO.Clearance.DisplayClearanceDTO;
import au.edu.utas.costing_tool.Enums.Project.AMCMenzies;
import au.edu.utas.costing_tool.Enums.Project.AMCNationalCentre;
import au.edu.utas.costing_tool.Enums.Project.Category1Subtype;
import au.edu.utas.costing_tool.Enums.Project.ProjectCategory;
import au.edu.utas.costing_tool.Enums.Project.ResearchEntity;
import au.edu.utas.costing_tool.Enums.Project.RhdInvolvement;
import au.edu.utas.costing_tool.Enums.Project.YearEndType;
import au.edu.utas.costing_tool.Enums.Researcher.Title;
import au.edu.utas.costing_tool.Mapper.ClearanceMapper;
import au.edu.utas.costing_tool.Model.Entity.Unit;
import au.edu.utas.costing_tool.Model.Project.EthicsChecklist;
import au.edu.utas.costing_tool.Model.Project.FORCodes;
import au.edu.utas.costing_tool.Model.Project.Project;
import au.edu.utas.costing_tool.Model.Project.ResearchAreas;
import au.edu.utas.costing_tool.Model.Project.SEOCodes;
import au.edu.utas.costing_tool.Model.Researcher.Researcher;
import au.edu.utas.costing_tool.Service.ProjectService;


@AutoConfigureMockMvc
@WebMvcTest(ClearanceController.class)
public class ClearanceControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClearanceMapper clearanceMapper; 

    @MockBean
    private ProjectService projectService; 

    private static final ObjectWriter writer = new ObjectMapper().writer();

    private static final String api = "/clearance/";

    @Test
    public void fetchClearanceForm_test() throws Exception
    {
        List<Long> ids = Arrays.asList(1L);

        ids.forEach( id -> {
            Project project = Project.builder()
                .id(id)
                .name(new StringBuilder()
                        .append("project ")
                        .append(id)
                        .toString())
                .description("test project")
                .herdc("herdc string")
                .fundingBody("funding body")
                .scheme("scheme")
                .contactName("Dr. Contact Name")
                .contactName("Contact.Name@email.com")
                .leadResearcherName("Dr. Lead Researcher")
                .category(ProjectCategory.CONSULTANCY)
                .category1Subtype(Category1Subtype.NONE)
                .amcMenzies(AMCMenzies.NONE)
                .amcNationalCentre(AMCNationalCentre.NCMEH)
                .profitMargin(0.0)
                .startDate(LocalDate.of(2022, Month.JANUARY, 1))
                .endDate(LocalDate.of(2023, Month.DECEMBER, 31))
                .yearEnd(YearEndType.CALENDAR)
                .utasCashContribution(10.0)
                .partnerCashContribution(10.0)
                .entity(ResearchEntity.IMAS)
                .crowdFundingProvider(null)
                .forCodes(FORCodes.builder()
                    .code1(123456)
                    .percent1(33.0)
                    .code2(000000)
                    .percent2(33.0)
                    .code3(987654)
                    .percent3(33.0)
                    .build()
                )
                .seoCodes(SEOCodes.builder()
                    .code1(123456)
                    .percent1(33.0)
                    .code2(000000)
                    .percent2(33.0)
                    .code3(987654)
                    .percent3(33.0)
                    .build()
                )
                .researchAreas(ResearchAreas.builder()
                    .appliedResearch(25.0)
                    .experimentalDevelopment(25.0)
                    .strategicBasic(25.0)
                    .pureBasic(25.0)
                    .build())
                .ethicsChecklist(EthicsChecklist.builder()
                    .human(false)
                    .humanRef(null)
                    .animal(true)
                    .animalRef(null)
                    .drugs(false)
                    .clinicalTrial(false)
                    .build())
                .contributions(null)
                .expenses(null)
                .externalResearchers(null)
                .rhdInvolvement(RhdInvolvement.SCHOLARSHIP)
                .rhdUnit(Unit.builder()
                    .id(1L)
                    .name("Institute for Marine and Antarctic Studies")
                    .abbrev("IMAS")
                    .head(Researcher.builder()
                        .staffID(1L)
                        .title(Title.DR)
                        .firstName("Unit")
                        .lastName("Head")
                        .contracts(null)
                        .build())
                    .build())
                .dvcrCash(0.0)
                .build();

            
            DisplayClearanceDTO dto = DisplayClearanceDTO.builder()
                .projectDetails(DisplayClearanceDTO.ProjectDetails.builder()
                    .category(project.getCategory().toString())
                    .projectTitle(project.getName())
                    .description(project.getDescription())
                    .herdc(project.getHerdc())
                    .startDate(project.getStartDate().toString())
                    .endDate(project.getEndDate().toString())
                    .fundingBody(project.getFundingBody())
                    .contactName(project.getContactName())
                    .contactEmail(project.getContactEmail())
                    .build()
                )
                .leadInvestigator(DisplayClearanceDTO.Investigator.builder()
                    .name(project.getLeadResearcherName())
                    .organisation(project.getLeadResearcherOrg())
                    .fte(null)
                    .build()
                )
                .investigators(null)
                .externalInvestigators(null)
                .rhdStudents(null)
                .researchCodes(DisplayClearanceDTO.ResearchCodes.builder()
                    .forCodes(Map.ofEntries(
                        Map.entry(  project.getForCodes().getCode1(),
                                    project.getForCodes().getPercent2()),
                        Map.entry(  project.getForCodes().getCode2(),
                                    project.getForCodes().getPercent2()),
                        Map.entry(  project.getForCodes().getCode3(),
                                    project.getForCodes().getPercent3())
                    ))
                    .seoCodes(Map.ofEntries(
                        Map.entry(  project.getSeoCodes().getCode1(),
                                    project.getSeoCodes().getPercent2()),
                        Map.entry(  project.getSeoCodes().getCode2(),
                                    project.getSeoCodes().getPercent2()),
                        Map.entry(  project.getSeoCodes().getCode3(),
                                    project.getSeoCodes().getPercent3())
                    ))
                    .appliedResearch(project.getResearchAreas().getAppliedResearch())
                    .experimentalDevelopment(project.getResearchAreas().getExperimentalDevelopment())
                    .strategicBasic(project.getResearchAreas().getStrategicBasic())
                    .pureBasic(project.getResearchAreas().getPureBasic())
                    .build()
                )
                .ethics(DisplayClearanceDTO.Ethics.builder()
                    .human(project.getEthicsChecklist().getHuman())
                    .humanRefNo(project.getEthicsChecklist().getHumanRef())
                    .animal(project.getEthicsChecklist().getAnimal())
                    .animalRefNo(project.getEthicsChecklist().getAnimalRef())
                    .controlledDrugs(project.getEthicsChecklist().getDrugs())
                    .clinicalTrial(project.getEthicsChecklist().getClinicalTrial())
                    .build()
                )
                .costs(DisplayClearanceDTO.ProjectCosts.builder()
                    .fundingBodyTotal(null)
                    .utasShare(null)
                    .overheads(null)
                    .paidTo(null)
                    .profit(null)
                    .inKindUTAS(null)
                    .inKindPartner(null)
                    .cashUTAS(null)
                    .cashDVCR(project.getDvcrCash())
                    .cashPartner(null)
                    .actualCost(null)
                    .build()
                )
                .externalPayments(DisplayClearanceDTO.ExternalPayments.builder()
                    .thirdParties(null)
                    .parties(null)
                    .build()
                )
                .build();


            Mockito
                .when(projectService.findProject(id))
                .thenReturn(project);

            Mockito
                .when(clearanceMapper.map(project))
                .thenReturn(dto);
            
            String expectedResponse = null;
            try {
                expectedResponse = writer.writeValueAsString(dto);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return;
            }

            String endpoint = new StringBuilder()
                .append(api)
                .append(id)
                .toString();
            
            try {
                this.mvc
                    .perform(get(endpoint))
                    //.andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedResponse, true));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        });
    }

    /*
    @Test
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
    */
}