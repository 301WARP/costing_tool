package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import au.edu.utas.costing_tool.DTO.Contribution.CasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.Contribution.ContributionDetailsDTO;
import au.edu.utas.costing_tool.DTO.Contribution.NonCasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.Contribution.RHDDetailsDTO;
import au.edu.utas.costing_tool.Model.Contract.Casual;
import au.edu.utas.costing_tool.Model.Contract.Contract;
import au.edu.utas.costing_tool.Model.Contract.NonCasual;
import au.edu.utas.costing_tool.Model.Contract.RHD;
import au.edu.utas.costing_tool.Model.Contribution.AnnualContribution;
import au.edu.utas.costing_tool.Model.Contribution.Contribution;

@Mapper
public interface ResearcherDetailsMapper
{
    // TODO(Andrew): Should be achievable with subclass mapping
    default
    ContributionDetailsDTO
    map(Contribution contribution)
    {
        Contract contract = contribution.getContract();

        if (contract == null) {
            return null;
        } else if (contract instanceof NonCasual) {
            return this .nonCasualToNonCasualDetailsDTO(
                contribution,
                (NonCasual)contract
            );
        } else if (contract instanceof Casual) {
            return this .casualToCasualDetailsDTO(
                contribution,
                (Casual)contract
            );
        } else if (contract instanceof RHD) {
            return this .RHDToRHDDetailsDTO(
                contribution,
                (RHD)contract
            );
        } else {
            return null;
        }
    }


    @Mapping(target="role", source="contribution.role")
    @Mapping(target="staffType", source="contract.staffType")
    @Mapping(target="classification", source="contract.classification")
    @Mapping(   target="step", source="contract.step",
                qualifiedByName="enumToInteger")
    @Mapping(target="startingSalary", source="contract.startingSalary")
    @Mapping(target="wageAdjustment", source="contribution.wageAdjustment")
    @Mapping(target="salaryOnCostRate", source="contribution.onCostRate")
    @Mapping(   target="annualContributions",
                source="contribution.annualContributions",
                qualifiedByName="annualContributionsMap")
    NonCasualDetailsDTO
    nonCasualToNonCasualDetailsDTO( Contribution contribution,
                                    NonCasual contract);


    @Mapping(target="role", source="contribution.role")
    @Mapping(target="staffType", source="contract.staffType")
    @Mapping(target="classification", source="contract.classification")
    @Mapping(   target="payCode", source="contract.payCode",
                qualifiedByName="enumToInteger")
    @Mapping(target="hourlyRate", source="contract.hourlyRate")
    @Mapping(target="wageAdjustment", source="contribution.wageAdjustment")
    @Mapping(target="salaryOnCostRate", source="contribution.onCostRate")
    @Mapping(   target="annualContributions",
                source="contribution.annualContributions",
                qualifiedByName="annualContributionsMap")
    CasualDetailsDTO
    casualToCasualDetailsDTO(   Contribution contribution,
                                Casual contract);
    

    @Mapping(target="role", source="contribution.role")
    @Mapping(target="classification", source="contract.classification")
    @Mapping(target="wageExpense", source="contract.annualSalary")
    @Mapping(   target="annualContributions",
                source="contribution.annualContributions",
                qualifiedByName="annualContributionsMap")
    RHDDetailsDTO
    RHDToRHDDetailsDTO(Contribution contribution, RHD contract);


    @Named("enumToInteger")
    static Integer enumToInteger(Enum<?> e)
    {
        return e.ordinal();
    }


    @Named("annualContributionsMap")
    static
    Map<Integer, Double>
    annualContributionsMap(List<AnnualContribution> acs)
    {
        return acs
            .stream()
            .collect(Collectors.toMap(
                AnnualContribution::getYear,
                AnnualContribution::getUnits
            ));
    }
}