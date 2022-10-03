package au.edu.utas.costing_tool.costing_tool.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import au.edu.utas.costing_tool.Enums.AMCMenzies;
import au.edu.utas.costing_tool.Enums.AMCNationalCentre;
import au.edu.utas.costing_tool.Enums.Category1Subtype;
import au.edu.utas.costing_tool.Enums.ProjectCategory;
import au.edu.utas.costing_tool.Enums.ResearchEntity;
import au.edu.utas.costing_tool.Enums.RhdInvolvement;
import au.edu.utas.costing_tool.Enums.Title;
import au.edu.utas.costing_tool.Enums.YearEndType;
import au.edu.utas.costing_tool.Model.EthicsChecklist;
import au.edu.utas.costing_tool.Model.FORCodes;
import au.edu.utas.costing_tool.Model.Project;
import au.edu.utas.costing_tool.Model.ResearchAreas;
import au.edu.utas.costing_tool.Model.Researcher;
import au.edu.utas.costing_tool.Model.SEOCodes;
import au.edu.utas.costing_tool.Model.Unit;
import au.edu.utas.costing_tool.Repository.ProjectRepository;
import au.edu.utas.costing_tool.Repository.ResearcherRepository;
import au.edu.utas.costing_tool.Service.ProjectService;


@SpringBootTest(classes=ProjectService.class)
public class ProjectServiceTest
{
    @MockBean
    private ProjectRepository projectRepos;

    @MockBean
    private ResearcherRepository researcherRepos;

    @Autowired
    private ProjectService projectService;


    @Test
    public void findProject_test() throws Exception
    {
        Long id = 1L;

        Project expected = Project.builder()
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

        Mockito
            .when(projectRepos.findById(id))
            .thenReturn(Optional.of(expected));
        
        Project response = projectService.findProject(id);

        Assertions
            .assertThat(response)
            .usingRecursiveComparison()
            .isEqualTo(expected);
    }
}