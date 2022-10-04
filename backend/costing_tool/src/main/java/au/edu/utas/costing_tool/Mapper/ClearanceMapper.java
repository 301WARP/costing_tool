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
import org.mapstruct.MappingTarget;
import org.mapstruct.Qualifier;

import au.edu.utas.costing_tool.DTO.DisplayClearanceDTO;
import au.edu.utas.costing_tool.DTO.UpdateClearanceDTO;
import au.edu.utas.costing_tool.Enums.ContractType;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Model.EthicsChecklist;
import au.edu.utas.costing_tool.Model.ExternalResearcher;
import au.edu.utas.costing_tool.Model.FORCodes;
import au.edu.utas.costing_tool.Model.Project;
import au.edu.utas.costing_tool.Model.Researcher;
import au.edu.utas.costing_tool.Model.SEOCodes;


@Mapper
public interface ClearanceMapper
{
    @Mapping(source="project", target="projectDetails", qualifiedBy=ProjectDetailsMap.class)
    @Mapping(source="project", target="leadInvestigator", qualifiedBy=InvestigatorMap.LeadProxy.class)
    @Mapping(source="project", target="investigators", qualifiedBy=InvestigatorMap.ListProxy.class)
    @Mapping(source="project.externalResearchers", target="externalInvestigators", qualifiedBy=InvestigatorMap.External.class)
    @Mapping(source="project", target="rhdStudents", qualifiedBy=InvestigatorMap.RhdProxy.class)
    @Mapping(source="project", target="researchCodes", qualifiedBy=ResearchCodesMap.class)
    @Mapping(source="ethicsChecklist", target="ethics", qualifiedBy=EthicsMap.class)
    //@Mapping(source="ciEndorsement", target="ciEndorsement", qualifiedBy=CiEndorsementMap.class)
    @BeanMapping(ignoreByDefault=true)
    DisplayClearanceDTO
    map(Project project);


    // =============================================================================
    // Project Details
    // =============================================================================

    @Mapping(source="category", target="category")
    @Mapping(source="name", target="projectTitle")
    @Mapping(source="herdc", target="herdc")
    @Mapping(source="description", target="description")
    @Mapping(source="startDate", target="startDate")
    @Mapping(source="endDate", target="endDate")
    @Mapping(source="fundingBody", target="fundingBody")
    @Mapping(source="scheme", target="scheme")
    @Mapping(source="contactName", target="contactName")
    @Mapping(source="contactEmail", target="contactEmail")
    @BeanMapping(ignoreByDefault=true)
    @ProjectDetailsMap
    DisplayClearanceDTO.ProjectDetails
    projectDetailsMap(Project project);


    // =============================================================================
    // Investigators
    // =============================================================================

    @InvestigatorMap.LeadProxy
    default DisplayClearanceDTO.Investigator
    leadInvestigatorMapProxy(Project project)
    {
        return DisplayClearanceDTO.Investigator.builder()
            .name(project.getLeadResearcherName())
            .organisation(project.getLeadResearcherOrg())
            .build();
    }


    @InvestigatorMap.ListProxy
    default List<DisplayClearanceDTO.Investigator>
    investigatorsMapProxy(Project project)
    {
        return project.getContributions()
            .stream()
            .map(c -> c.getContract())
            .filter(c -> c.getContractType() != ContractType.RHD)
            .map(c -> this.investigatorMap(c, project))
            .collect(Collectors.toList());
    }


    @InvestigatorMap.RhdProxy
    default DisplayClearanceDTO.RhdStudents
    rhdStudentsMapProxy(Project project)
    {
        List<String> names = project.getContributions()
            .stream()
            .map(c -> c.getContract())
            .filter(c -> c.getContractType() == ContractType.RHD)
            .map(c -> this.investigatorNameMap(c.getResearcher()))
            .collect(Collectors.toList());
        
        String involvement = project.getRhdInvolvement() == null
            ? null
            : project.getRhdInvolvement().toString();
        
        String unit = project.getRhdUnit() == null
            ? null
            : project.getRhdUnit().getName();

        return DisplayClearanceDTO.RhdStudents.builder()
            .involvement(involvement)
            .unit(unit)
            .names(names)
            .build();
    }


    // TODO(Andrew): how to calculate fte for entire project? Average?
    @Mapping(source="researcher", target="name", qualifiedBy=InvestigatorMap.Name.class)
    @Mapping(source="unit.name", target="organisation")
    @BeanMapping(ignoreByDefault=true)
    @InvestigatorMap
    DisplayClearanceDTO.Investigator
    investigatorMap(Contract contract, @Context Project project);


    @Mapping(source="name", target="name")
    @Mapping(source="organisation", target="organisation")
    @BeanMapping(ignoreByDefault=true)
    @InvestigatorMap.External
    List<DisplayClearanceDTO.Investigator>
    externalInvestigatorMap(List<ExternalResearcher> researcher);


    @InvestigatorMap.Name
    default String
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


    // =============================================================================
    // Researcher Codes
    // =============================================================================

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


    // =============================================================================
    // Ethics
    // =============================================================================

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


    /*
    // =============================================================================
    // Endorsements
    // =============================================================================

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
    @Mapping(source="endorser", target="endorserName", qualifiedBy=InvestigatorMap.Name.class)
    @Mapping(source="endorsed", target="endorsed")
    @Mapping(source="endorsementDate", target="endorsementDate")
    @DirectorEndorsementMap
    DisplayClearanceDTO.DirectorEndorsement
    directorEndorsementMap(DirectorEndorsement endorsement);

    @Mapping(source="college.name", target="college")
    @Mapping(source="endorser", target="endorserName", qualifiedBy=InvestigatorMap.Name.class)
    @Mapping(source="endorsed", target="endorsed")
    @Mapping(source="date", target="endorsementDate")
    @CollegeEndorsementMap
    DisplayClearanceDTO.CollegeEndorsement
    collegeEndorsementMap(CollegeEndorsement endorsement);
    */



    // =============================================================================
    // Annotations
    // =============================================================================

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ProjectDetailsMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface InvestigatorMap {
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
        public @interface External {}
        public External external() default @External();
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




    @Mapping(source="projectDetails.herdc", target="herdc")
    @Mapping(source="projectDetails.fundingBody", target="fundingBody")
    @Mapping(source="projectDetails.scheme", target="scheme")
    @Mapping(source="projectDetails.contactName", target="contactName")
    @Mapping(source="projectDetails.contactEmail", target="contactEmail")
    @Mapping(source="ethics.human", target="ethicsChecklist.human")
    @Mapping(source="ethics.humanRefNo", target="ethicsChecklist.humanRef")
    @Mapping(source="ethics.animal", target="ethicsChecklist.animal")
    @Mapping(source="ethics.animalRefNo", target="ethicsChecklist.animalRef")
    @Mapping(source="ethics.controlledDrugs", target="ethicsChecklist.drugs")
    @Mapping(source="ethics.clinicalTrial", target="ethicsChecklist.clinicalTrial")
    @Mapping(source="costs.cashDVCR", target="dvcrCash")
    @Mapping(source="externalInvestigators", target="externalResearchersList", qualifiedBy=ExternalInvestigatorListMap.class)
    @BeanMapping(ignoreByDefault=true)
    Project
    map(UpdateClearanceDTO dto, @MappingTarget Project project);


    @Mapping(source="name", target="name")
    @Mapping(source="organisation", target="organisation")
    @BeanMapping(ignoreByDefault=true)
    @ExternalInvestigatorListMap
    List<ExternalResearcher>
    dtoToExternalListInvestigatorMap(List<UpdateClearanceDTO.Investigator> investigator);
    
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ExternalInvestigatorListMap {}
}