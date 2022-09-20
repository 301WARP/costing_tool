package au.edu.utas.costing_tool.Mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

import au.edu.utas.costing_tool.DTO.DisplayClearanceDTO;
import au.edu.utas.costing_tool.Enums.ContractType;
import au.edu.utas.costing_tool.Model.CIEndorsement;
import au.edu.utas.costing_tool.Model.CollegeEndorsement;
import au.edu.utas.costing_tool.Model.DirectorEndorsement;
import au.edu.utas.costing_tool.Model.EthicsChecklist;
import au.edu.utas.costing_tool.Model.FORCodes;
import au.edu.utas.costing_tool.Model.Project;
import au.edu.utas.costing_tool.Model.Researcher;
import au.edu.utas.costing_tool.Model.SEOCodes;
//import au.edu.utas.costing_tool.Model.DirectorEndorsement;

@Mapper
//@Data
public interface ClearanceMapper
{
    /*
    @Autowired
    private final ContributionService contributionService;
    */

    @Mapping(source="project", target="projectDetails", qualifiedBy=ProjectDetailsMap.class)
    @Mapping(source="project", target="leadInvestigator", qualifiedBy=InvestogatorMap.LeadProxy.class)
    @Mapping(source="project", target="investigators", qualifiedBy=InvestogatorMap.ListProxy.class)
    @Mapping(source="project", target="rhdStudents", qualifiedBy=InvestogatorMap.RhdProxy.class)
    @Mapping(source="project", target="researchCodes", qualifiedBy=ResearchCodesMap.class)
    @Mapping(source="ethicsChecklist", target="ethics", qualifiedBy=EthicsMap.class)
    @Mapping(source="ciEndorsement", target="ciEndorsement", qualifiedBy=CiEndorsementMap.class)
    @BeanMapping(ignoreByDefault=true)
    DisplayClearanceDTO
    map(Project project);

    // TODO(Andrew): Incomplete source information
    @Mapping(source="category", target="category")
    @Mapping(source="name", target="projectTitle")
    // TODO(Andrew): @Mapping(source="", target="herdc")
    @Mapping(source="description", target="description")
    @Mapping(source="startDate", target="startDate")
    @Mapping(source="endDate", target="endDate")
    // TODO(Andrew): @Mapping(source="", target="fundingBody")
    // TODO(Andrew): @Mapping(source="", target="scheme")
    // TODO(Andrew): @Mapping(source="", target="contactName")
    // TODO(Andrew): @Mapping(source="", target="contactEmail")
    @BeanMapping(ignoreByDefault=true)
    @ProjectDetailsMap
    DisplayClearanceDTO.ProjectDetails
    projectDetailsMap(Project project);


    @InvestogatorMap.LeadProxy
    default DisplayClearanceDTO.Investigator
    leadInvestigatorMapProxy(Project project)
    {
        return this.investigatorMap(project.getLeadResearcher(), project);
    }

    @InvestogatorMap.ListProxy
    default List<DisplayClearanceDTO.Investigator>
    investigatorsMapProxy(Project project)
    {
        List<Researcher> researchers = project.getContributions()
            .stream()
            .map(c -> c.getContract())
            .filter(c -> c.getContractType() != ContractType.RHD)
            .map(c -> c.getResearcher())
            .filter(r -> r != project.getLeadResearcher())
            .collect(Collectors.toList());

        return researchers.stream()
            .map(r -> this.investigatorMap(r, project))
            .collect(Collectors.toList());
    }

    @InvestogatorMap.RhdProxy
    default List<DisplayClearanceDTO.Investigator>
    rhdStudentsMapProxy(Project project)
    {
        List<Researcher> researchers = project.getContributions()
            .stream()
            .map(c -> c.getContract())
            .filter(c -> c.getContractType() == ContractType.RHD)
            .map(c -> c.getResearcher())
            .filter(r -> r != project.getLeadResearcher())
            .collect(Collectors.toList());

        return researchers.stream()
            .map(r -> this.investigatorMap(r, project))
            .collect(Collectors.toList());
    }


    // TODO(Andrew):    cannot do
    //  organisation - not stored
    //  fte - how to calculate for entire project? Average?
    //  involvement - not stored
    @Mapping(source="researcher", target="name", qualifiedBy=InvestogatorMap.Name.class)
    @Mapping(source="researcher", target="type", qualifiedBy=InvestogatorMap.Type.class)
    @BeanMapping(ignoreByDefault=true)
    @InvestogatorMap
    DisplayClearanceDTO.Investigator
    investigatorMap(Researcher researcher, @Context Project project);


    @InvestogatorMap.Name
    static String
    investigatorNameMap(Researcher r)
    {
        if (r == null)
            return null;
        
        return new StringJoiner(" ")
            .add(r.getTitle().toString())
            .add(r.getFirstName())
            .add(r.getLastName())
            .toString();
    }

    @InvestogatorMap.Type
    default String
    investigatorTypeMap(Researcher r, @Context Project p)
    {
        // TODO(Andrew):    Is this possible if the lead researcher is employed
        //                  under multiple contracts?
        return null;
    }


    @Mapping(source="forCodes", target="forCodes", qualifiedBy=ForCodesMap.class)
    @Mapping(source="seoCodes", target="seoCodes", qualifiedBy=SeoCodesMap.class)
    @Mapping(source="researchAreas.appliedResearch", target="appliedResearch")
    @Mapping(source="researchAreas.experimentalDevelopment", target="experimentalDevelopment")
    @Mapping(source="researchAreas.strategicBasic", target="strategicBasic")
    @Mapping(source="researchAreas.pureBasic", target="pureBasic")
    @ResearchCodesMap
    DisplayClearanceDTO.ResearchCodes
    researchCodesMap(Project project);


    @ForCodesMap
    default Map<Integer, Double>
    forCodesMap(FORCodes codes)
    {
        if (codes == null)
            return null;
        
        Map<Integer, Double> codeMap = new HashMap<Integer, Double>();

        if (codes.getCode1() != null)
            codeMap.put(codes.getCode1(), codes.getPercent1());
        if (codes.getCode2() != null)
            codeMap.put(codes.getCode2(), codes.getPercent2());
        if (codes.getCode3() != null)
            codeMap.put(codes.getCode3(), codes.getPercent3());
        
        return codeMap;
    }


    @SeoCodesMap
    default Map<Integer, Double>
    seoCodesMap(SEOCodes codes)
    {
        if (codes == null)
            return null;
        
        Map<Integer, Double> codeMap = new HashMap<Integer, Double>();

        if (codes.getCode1() != null)
            codeMap.put(codes.getCode1(), codes.getPercent1());
        if (codes.getCode2() != null)
            codeMap.put(codes.getCode2(), codes.getPercent2());
        if (codes.getCode3() != null)
            codeMap.put(codes.getCode3(), codes.getPercent3());
        
        return codeMap;
    }


    @Mapping(source="human", target="human")
    @Mapping(source="humanRef", target="humanRefNo")
    @Mapping(source="animal", target="animal")
    @Mapping(source="animalRef", target="animalRefNo")
    @Mapping(source="drugs", target="controlledDrugs")
    @Mapping(source="clinicalTrial", target="clinicalTrial")
    @BeanMapping(ignoreByDefault=true)
    @EthicsMap
    DisplayClearanceDTO.Ethics
    ethicsMap(EthicsChecklist ethics);



    @Mapping(source="riskAssessment", target="riskAssessment")
    @Mapping(source="utasInsurance", target="utasInsurance")
    @Mapping(source="defenceStrategicGoods", target="dsgl")
    @Mapping(source="conflictOfInterest", target="conflictOfInterest")
    @Mapping(source="foreignPrincipals", target="foreignPrincipals")
    @Mapping(source="ciEndorsement", target="endorsed")
    @Mapping(source="ciEndorsementDate", target="endorsementDate")
    @CiEndorsementMap
    DisplayClearanceDTO.CiEndorsement
    ciEndorsementMap(CIEndorsement endorsement);
    

    @Mapping(source="projectCosts", target="projectCosts")
    @Mapping(source="facilities", target="facilities")
    @Mapping(source="blessing", target="blessing")
    @Mapping(source="workload", target="workload")
    @Mapping(source="ethics", target="ethics")
    @Mapping(source="conflictOfInterest", target="conflictOfInterest")
    @Mapping(source="unit.name", target="unit")
    @Mapping(source="endorser", target="endorserName", qualifiedBy=InvestogatorMap.Name.class)
    @Mapping(source="endorsed", target="endorsed")
    @Mapping(source="endorsementDate", target="endorsementDate")
    @DirectorEndorsementMap
    DisplayClearanceDTO.DirectorEndorsement
    directorEndorsementMap(DirectorEndorsement endorsement);

    @Mapping(source="college.name", target="college")
    @Mapping(source="endorser", target="endorserName", qualifiedBy=InvestogatorMap.Name.class)
    @Mapping(source="endorsed", target="endorsed")
    @Mapping(source="date", target="endorsementDate")
    @CollegeEndorsementMap
    DisplayClearanceDTO.CollegeEndorsement
    collegeEndorsementMap(CollegeEndorsement endorsement);




    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ProjectDetailsMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface InvestogatorMap {
        @Qualifier
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.CLASS)
        public @interface LeadProxy {}
        public LeadProxy leadProxy() default @LeadProxy();

        @Qualifier
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.CLASS)
        public @interface ListProxy {}
        public ListProxy listProxt() default @ListProxy();

        @Qualifier
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.CLASS)
        public @interface RhdProxy {}
        public RhdProxy rhdProxy() default @RhdProxy();

        @Qualifier
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.CLASS)
        public @interface Name {}
        public Name name() default @Name();

        @Qualifier
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.CLASS)
        public @interface Type {}
        public Type type() default @Type();
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ResearchCodesMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ForCodesMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface SeoCodesMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface EthicsMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface CiEndorsementMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface DirectorEndorsementMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface CollegeEndorsementMap {}
}