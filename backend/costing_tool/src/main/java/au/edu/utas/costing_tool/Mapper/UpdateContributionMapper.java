package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Qualifier;
import org.mapstruct.SubclassMapping;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.Contribution.UpdateResearcherDTO;
import au.edu.utas.costing_tool.DTO.Contribution.UpdateRhdDTO;
import au.edu.utas.costing_tool.DTO.Contribution.UpdateStaffDTO;
import au.edu.utas.costing_tool.DTO.Contribution.UpdateTbaDTO;
import au.edu.utas.costing_tool.Enums.Contract.CasualClassification;
import au.edu.utas.costing_tool.Enums.Contract.CasualStaffType;
import au.edu.utas.costing_tool.Enums.Contract.NonCasualClassification;
import au.edu.utas.costing_tool.Enums.Contract.NonCasualStaffType;
import au.edu.utas.costing_tool.Enums.Contract.PayCode;
import au.edu.utas.costing_tool.Enums.Contract.RHDIncomeStream;
import au.edu.utas.costing_tool.Enums.Contract.Step;
import au.edu.utas.costing_tool.Model.Contract.Casual;
import au.edu.utas.costing_tool.Model.Contract.Contract;
import au.edu.utas.costing_tool.Model.Contract.NonCasual;
import au.edu.utas.costing_tool.Model.Contract.RHD;
import au.edu.utas.costing_tool.Model.Contract.Staff;
import au.edu.utas.costing_tool.Model.Contribution.AnnualContribution;
import au.edu.utas.costing_tool.Model.Contribution.Contribution;


@Mapper
public interface UpdateContributionMapper
{
    // =========================================================================
    // Mappings
    // =========================================================================

    @SubclassMapping(source=UpdateStaffDTO.class, target=Contribution.class)
    @SubclassMapping(source=UpdateTbaDTO.class, target=Contribution.class)
    @SubclassMapping(source=UpdateRhdDTO.class, target=Contribution.class)
    @BeanMapping(ignoreByDefault=true)
    Contribution map(UpdateResearcherDTO dto, @Context Long projectId);


    @Mapping(target="role", source="role")
    @Mapping(target="contractID", source="contractID")
    @Mapping(target="projectID", expression="java(projectId)")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="wageAdjustment", source="wageAdjustment")
    @Mapping(target="onCostRate", source="onCostRate")
    @Mapping(   target="annualContributions", source="annualContributions",
                qualifiedBy=AnnualContributionsMap.class)
    @BeanMapping(ignoreByDefault=true)
    Contribution map(   UpdateStaffDTO dto,
                        @Context Long projectId);

    @Mapping(target="role", source="role")
    @Mapping(   target="contract", source="dto",
                qualifiedBy=ContractMap.class)
    @Mapping(target="projectID", expression="java(projectId)")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="wageAdjustment", source="wageAdjustment")
    @Mapping(target="onCostRate", source="onCostRate")
    @Mapping(   target="annualContributions", source="annualContributions",
                qualifiedBy=AnnualContributionsMap.class)
    @BeanMapping(ignoreByDefault=true)
    Contribution map(   UpdateTbaDTO dto,
                        @Context Long projectId);

    @Mapping(target="role", source="role")
    @Mapping(target="contractID", source="contractId")
    @Mapping(target="projectID", expression="java(projectId)")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="onCostRate", source="onCostRate")
    @Mapping(   target="annualContributions", source="annualContributions",
                qualifiedBy=AnnualContributionsMap.class)
    @BeanMapping(ignoreByDefault=true)
    Contribution map(   UpdateRhdDTO dto, 
                        @Context Long projectId);

    /*
    @Mapping(target="role", source="role")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="wageAdjustment", source="wageAdjustment")
    @Mapping(target="onCostRate", source="onCostRate")
    @Mapping(   target="annualContributions", source="annualContributions",
                qualifiedByName="mapAnnualContributions")
    @Mapping(target="contract", ignore=true)
    @Mapping(target="project", ignore=true)
    @Mapping(target="contractID", ignore=true)
    @Mapping(target="projectID", ignore=true)
    Contribution updateResearcherDTOToContribution(UpdateResearcherDTO dto);
    */


    // =========================================================================
    // Methods
    // =========================================================================

    @AnnualContributionsMap
    static
    List<AnnualContribution>
    mapAnnualContributions(Map<Integer,Double> acs)
    {
        if (acs == null)
            return null;
        
        return acs.entrySet().stream()
            .map(e -> AnnualContribution.builder()
                .year(e.getKey())
                .units(e.getValue())
                .build()
            )
            .collect(Collectors.toList());
    }


    @ContractMap
    static
    Contract
    contractMap(UpdateTbaDTO dto)
    {
        if (dto == null)
            return null;
        
        Contract contract = null;

        switch(dto.getContract()) {
            case "NON_CASUAL":
                contract = NonCasual.builder()
                    .staffType(
                        NonCasualStaffType.valueOf(
                            dto.getStaffType()
                        )
                    )
                    .classification(
                        NonCasualClassification.valueOf(
                            dto.getClassification()
                        )
                    )
                    .step(
                        Step.valueOf(
                            dto.getStep()
                        )
                    )
                    .build();
                break;
            case "CASUAL":
                contract = Casual.builder()
                    .staffType(
                        CasualStaffType.valueOf(
                            dto.getStaffType()
                        )
                    )
                    .classification(
                        CasualClassification.valueOf(
                            dto.getClassification()
                        )
                    )
                    .payCode(
                        PayCode.valueOf(
                            dto.getStep()
                        )
                    )
                    .build();
                break;
            case "RHD":
                contract = RHD.builder()
                    .classification(
                        RHDIncomeStream.valueOf(
                            dto.getClassification()
                        )
                    )
                    .wageExpense(dto.getWageExpense())
                    .build();
                break;
            default:
                break;
        }

        return contract;
    }


    // =========================================================================
    // Qualifiers
    // =========================================================================

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface AnnualContributionsMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ContractMap {}
}