package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.ProjectDetailsDTO;

import au.edu.utas.costing_tool.Model.FORCodes;
import au.edu.utas.costing_tool.Model.Project;
import au.edu.utas.costing_tool.Model.SEOCodes;


@Mapper
public interface ProjectDetailsMapper
{
    @Mapping(target="id", source="id")
    @Mapping(target="name", source="name")
    @Mapping(target="description", source="description")
    /*
    @Mapping(target="leadResearcherId", source="leadResearcher.staffID")
    @Mapping(target="leadResearcherTitle", source="leadResearcher.title")
    @Mapping(target="leadResearcherFirstName", source="leadResearcher.firstName")
    @Mapping(target="leadResearcherLastName", source="leadResearcher.lastName")
    */
    @Mapping(target="leadResearcherName", source="leadResearcherName")
    @Mapping(target="leadResearcherOrg", source="leadResearcherOrg")
    @Mapping(target="category", source="category")
    @Mapping(target="category1Subtype", source="category1Subtype")
    @Mapping(target="amcMenzies", source="amcMenzies")
    @Mapping(target="amcNationalCentre", source="amcNationalCentre")
    @Mapping(target="profitMargin", source="profitMargin")
    @Mapping(target="startDate", source="startDate")
    @Mapping(target="endDate", source="endDate")
    @Mapping(target="yearEnd", source="yearEnd")
    @Mapping(target="utasCashContribution", source="utasCashContribution")
    @Mapping(target="partnerCashContribution", source="partnerCashContribution")
    @Mapping(target="crowdFundingProvider", source="crowdFundingProvider")
    @Mapping(target="entity", source="entity")
    @Mapping(   target="forCodes", source="forCodes",
                qualifiedByName="forCodeEntityToDTOMap")
    @Mapping(   target="seoCodes", source="seoCodes",
                qualifiedByName="seoCodeEntityToDTOMap")
    @Mapping(target="appliedResearch", source="researchAreas.appliedResearch")
    @Mapping(target="experimentalDevelopment", source="researchAreas.experimentalDevelopment")
    @Mapping(target="strategicBasic", source="researchAreas.strategicBasic")
    @Mapping(target="pureBasic", source="researchAreas.pureBasic")
    public abstract ProjectDetailsDTO map(Project project);

    
    @Named("forCodeEntityToDTOMap")
    default
    Map<Integer, Double>
    forCodeEntityToDTOMap(FORCodes codes)
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


    @Named("seoCodeEntityToDTOMap")
    default
    Map<Integer, Double>
    seoCodeEntityToDTOMap(SEOCodes codes)
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




    @BeanMapping(ignoreByDefault=true)
    @Mapping(source="name", target="name")
    @Mapping(source="description", target="description")
    /*
    @Mapping(   source="leadResearcherId", target="leadResearcher",
                qualifiedByName="leadResearcherMap")
    */
    @Mapping(source="leadResearcherName", target="leadResearcherName")
    @Mapping(source="leadResearcherOrg", target="leadResearcherOrg")
    @Mapping(source="category", target="category")
    @Mapping(source="category1Subtype", target="category1Subtype")
    @Mapping(source="amcMenzies", target="amcMenzies")
    @Mapping(source="amcNationalCentre", target="amcNationalCentre")
    @Mapping(source="profitMargin", target="profitMargin")
    @Mapping(source="startDate", target="startDate")
    @Mapping(source="endDate", target="endDate")
    @Mapping(source="yearEnd", target="yearEnd")
    @Mapping(source="utasCashContribution", target="utasCashContribution")
    @Mapping(source="partnerCashContribution", target="partnerCashContribution")
    @Mapping(source="crowdFundingProvider", target="crowdFundingProvider")
    @Mapping(source="entity", target="entity")
    @Mapping(   source="forCodes", target="forCodes",
                qualifiedByName="forCodeDTOToEntityMap")
    @Mapping(   source="seoCodes", target="seoCodes",
                qualifiedByName="seoCodeDTOToEntityMap")
    @Mapping(source="appliedResearch", target="researchAreas.appliedResearch")
    @Mapping(   source="experimentalDevelopment",
                target="researchAreas.experimentalDevelopment")
    @Mapping(source="strategicBasic", target="researchAreas.strategicBasic")
    @Mapping(source="pureBasic", target="researchAreas.pureBasic")
    public abstract
    Project
    map(ProjectDetailsDTO dto, @MappingTarget Project project);

    @Named("forCodeDTOToEntityMap")
    //protected static
    default
    FORCodes 
    forCodeDTOToEntityMap(Map<Integer, Double> codeMap)
    {
        if (codeMap == null)
            return null;

        List<Map.Entry<Integer,Double>> codeList =
            new ArrayList<Map.Entry<Integer,Double>>(codeMap.entrySet());

        FORCodes codes = new FORCodes();

        if (codeList.size() > 0 && codeList.get(0) != null) {
            codes.setCode1(codeList.get(0).getKey());
            codes.setPercent1(codeList.get(0).getValue());
        } if (codeList.size() > 1 && codeList.get(1) != null) {
            codes.setCode2(codeList.get(1).getKey());
            codes.setPercent2(codeList.get(1).getValue());
        } if (codeList.size() > 2 && codeList.get(2) != null) {
            codes.setCode3(codeList.get(2).getKey());
            codes.setPercent3(codeList.get(2).getValue());
        }

        return codes;
    }
    
    @Named("seoCodeDTOToEntityMap")
    //protected static
    default
    SEOCodes 
    seoCodeDTOToEntityMap(Map<Integer, Double> codeMap)
    {
        if (codeMap == null)
            return null;
        
        List<Map.Entry<Integer,Double>> codeList =
            new ArrayList<Map.Entry<Integer,Double>>(codeMap.entrySet());

        SEOCodes codes = new SEOCodes();

        if (codeList.size() > 0 && codeList.get(0) != null) {
            codes.setCode1(codeList.get(0).getKey());
            codes.setPercent1(codeList.get(0).getValue());
        } if (codeList.size() > 1 && codeList.get(1) != null) {
            codes.setCode2(codeList.get(1).getKey());
            codes.setPercent2(codeList.get(1).getValue());
        } if (codeList.size() > 2 && codeList.get(2) != null) {
            codes.setCode3(codeList.get(2).getKey());
            codes.setPercent3(codeList.get(2).getValue());
        }

        return codes;
    }

    /*
    @Named("leadResearcherMap")
    //protected
    default
    Researcher
    leadResearcherMap(Long staffId)
    {
        if (staffId == null)
            return null;

        return  this.rRepos
                    .findById(staffId)
                    .orElse(null);
    }
    */
}