package au.edu.utas.costing_tool.Mapper;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import org.mapstruct.SubclassExhaustiveStrategy;
import org.mapstruct.SubclassMapping;

import au.edu.utas.costing_tool.DTO.Expense.AuditFeeDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.ConsumableDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.EquipmentPurchaseDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.ExpenseDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.ExternalContractorDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.FacilityCostDetailsDTO;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.Expense.LaboratoryHireDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.OtherCostsDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.PartnerOrganisationDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.RhdNonStipendCostDetailsDTO;
import au.edu.utas.costing_tool.DTO.Expense.TravelDetailsDTO;

import au.edu.utas.costing_tool.Enums.Expense.Units;

import au.edu.utas.costing_tool.Model.Expense.AnnualExpense;
import au.edu.utas.costing_tool.Model.Expense.Audit;
import au.edu.utas.costing_tool.Model.Expense.Consumables;
import au.edu.utas.costing_tool.Model.Expense.EquipmentPurchases;
import au.edu.utas.costing_tool.Model.Expense.Expense;
import au.edu.utas.costing_tool.Model.Expense.ExternalContractor;
import au.edu.utas.costing_tool.Model.Expense.Facility;
import au.edu.utas.costing_tool.Model.Expense.LaboratoryHire;
import au.edu.utas.costing_tool.Model.Expense.OtherCosts;
import au.edu.utas.costing_tool.Model.Expense.PartnerOrganisation;
import au.edu.utas.costing_tool.Model.Expense.RhdNonStipend;
import au.edu.utas.costing_tool.Model.Expense.Travel;

@Mapper
public interface ExpenseDetailsMapper
{
    /*
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
    */

    @SubclassMapping(source=Travel.class, target=TravelDetailsDTO.class)
    @SubclassMapping(source=LaboratoryHire.class, target=LaboratoryHireDetailsDTO.class)
    @SubclassMapping(source=Consumables.class, target=ConsumableDetailsDTO.class)
    @SubclassMapping(source=EquipmentPurchases.class, target=EquipmentPurchaseDetailsDTO.class)
    @SubclassMapping(source=ExternalContractor.class, target=ExternalContractorDetailsDTO.class)
    @SubclassMapping(source=OtherCosts.class, target=OtherCostsDetailsDTO.class)
    @SubclassMapping(source=Audit.class, target=AuditFeeDetailsDTO.class)
    @SubclassMapping(source=RhdNonStipend.class, target=RhdNonStipendCostDetailsDTO.class)
    @SubclassMapping(source=Facility.class, target=FacilityCostDetailsDTO.class)
    @SubclassMapping(source=PartnerOrganisation.class, target=PartnerOrganisationDetailsDTO.class)
    @BeanMapping(   ignoreByDefault=true,
                    subclassExhaustiveStrategy=SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
    public ExpenseDetailsDTO map(Expense expense);

    @SubclassMapping(target=Travel.class, source=TravelDetailsDTO.class)
    @SubclassMapping(target=LaboratoryHire.class, source=LaboratoryHireDetailsDTO.class)
    @SubclassMapping(target=Consumables.class, source=ConsumableDetailsDTO.class)
    @SubclassMapping(target=EquipmentPurchases.class, source=EquipmentPurchaseDetailsDTO.class)
    @SubclassMapping(target=ExternalContractor.class, source=ExternalContractorDetailsDTO.class)
    @SubclassMapping(target=OtherCosts.class, source=OtherCostsDetailsDTO.class)
    @SubclassMapping(target=Audit.class, source=AuditFeeDetailsDTO.class)
    @SubclassMapping(target=RhdNonStipend.class, source=RhdNonStipendCostDetailsDTO.class)
    @SubclassMapping(target=Facility.class, source=FacilityCostDetailsDTO.class)
    @SubclassMapping(target=PartnerOrganisation.class, source=PartnerOrganisationDetailsDTO.class)
    //@BeanMapping(ignoreByDefault=true)
    @BeanMapping(   ignoreByDefault=true,
                    subclassExhaustiveStrategy=SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
    public Expense map(ExpenseDetailsDTO dto);


    // =========================================================================
    // Expense to DTO Mappers
    // =========================================================================

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="numTravellers", source="numTravellers")
    @Mapping(target="departureDate", source="departureDate")
    @Mapping(target="returnDate", source="returnDate")
    @Mapping(target="returnFare", source="fare")
    @Mapping(target="accommodation", source="accommodation")
    @Mapping(target="carHire", source="carHire")
    @Mapping(target="meals", source="meals")
    TravelDetailsDTO
    travelDetailsMap(Travel expense);

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="costPerUnit", source="costPerUnit")
    @Mapping(   target="annualQuantity", source="annualExpenses",
                qualifiedBy=AnnualQuantityMap.class)
    LaboratoryHireDetailsDTO
    laboratoryHireDetailsMap(LaboratoryHire expense);

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="costPerUnit", source="costPerUnit")
    @Mapping(   target="annualQuantity", source="annualExpenses",
                qualifiedBy=AnnualQuantityMap.class)
    ConsumableDetailsDTO
    consumableDetailsMap(Consumables expense);

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="costPerUnit", source="costPerUnit")
    @Mapping(   target="annualQuantity", source="annualExpenses",
                qualifiedBy=AnnualQuantityMap.class)
    EquipmentPurchaseDetailsDTO
    equipmentPurchaseDetailsMap(EquipmentPurchases expense);

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="costPerUnit", source="costPerUnit")
    @Mapping(   target="annualQuantity", source="annualExpenses",
                qualifiedBy=AnnualQuantityMap.class)
    ExternalContractorDetailsDTO
    externalContractorMap(ExternalContractor expense);

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="costPerUnit", source="costPerUnit")
    @Mapping(   target="annualQuantity", source="annualExpenses",
                qualifiedBy=AnnualQuantityMap.class)
    OtherCostsDetailsDTO
    otherCostsMap(OtherCosts expense);

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="costPerUnit", source="costPerUnit")
    @Mapping(target="auditCostName", source="auditFee.type")
    @Mapping(   target="units", source="auditFee.units",
                qualifiedBy=UnitsToStringMap.class)
    @Mapping(   target="annualQuantity", source="annualExpenses",
                qualifiedBy=AnnualQuantityMap.class)
    AuditFeeDetailsDTO
    AuditMap(Audit expense);

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="costPerUnit", source="costPerUnit")
    @Mapping(   target="annualQuantity", source="annualExpenses",
                qualifiedBy=AnnualQuantityMap.class)
    RhdNonStipendCostDetailsDTO
    RhdNonStipendCostMap(RhdNonStipend expense);

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="costPerUnit", source="costPerUnit")
    @Mapping(target="facilityCostName", source="facilityCost.name")
    @Mapping(   target="units", source="facilityCost.units",
                qualifiedBy=UnitsToStringMap.class)
    @Mapping(   target="annualQuantity", source="annualExpenses",
                qualifiedBy=AnnualQuantityMap.class)
    FacilityCostDetailsDTO
    FacilityMap(Facility expense);

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(target="costPerUnit", source="costPerUnit")
    @Mapping(target="organisation", source="organisation")
    @Mapping(   target="annualQuantity", source="annualExpenses",
                qualifiedBy=AnnualQuantityMap.class)
    PartnerOrganisationDetailsDTO
    PartnerOrganisationMap(PartnerOrganisation expense);
    

    // =========================================================================
    // DTO to Expense Mappers
    // =========================================================================

    //@Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="numTravellers", target="numTravellers")
    @Mapping(source="departureDate", target="departureDate")
    @Mapping(source="returnDate", target="returnDate")
    @Mapping(source="returnFare", target="fare")
    @Mapping(source="accommodation", target="accommodation")
    @Mapping(source="carHire", target="carHire")
    @Mapping(source="meals", target="meals")
    @BeanMapping(ignoreByDefault=true)
    Travel
    travelDetailsMap(TravelDetailsDTO dto);

    //@Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="costPerUnit", target="costPerUnit")
    @Mapping(   source="annualQuantity", target="annualExpenses",
                qualifiedBy=AnnualExpenseMap.class)
    @BeanMapping(ignoreByDefault=true)
    LaboratoryHire
    laboratoryHireDetailsMap(LaboratoryHireDetailsDTO dto);

    @Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="costPerUnit", target="costPerUnit")
    @Mapping(   source="annualQuantity", target="annualExpenses",
                qualifiedBy=AnnualExpenseMap.class)
    @BeanMapping(ignoreByDefault=true)
    Consumables
    consumableDetailsMap(ConsumableDetailsDTO dto);

    @Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="costPerUnit", target="costPerUnit")
    @Mapping(   source="annualQuantity", target="annualExpenses",
                qualifiedBy=AnnualExpenseMap.class)
    @BeanMapping(ignoreByDefault=true)
    EquipmentPurchases
    equipmentPurchaseDetailsMap(EquipmentPurchaseDetailsDTO dto);

    @Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="costPerUnit", target="costPerUnit")
    @Mapping(   source="annualQuantity", target="annualExpenses",
                qualifiedBy=AnnualExpenseMap.class)
    @BeanMapping(ignoreByDefault=true)
    ExternalContractor
    externalContractorMap(ExternalContractorDetailsDTO dto);

    @Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="costPerUnit", target="costPerUnit")
    @Mapping(   source="annualQuantity", target="annualExpenses",
                qualifiedBy=AnnualExpenseMap.class)
    @BeanMapping(ignoreByDefault=true)
    OtherCosts
    otherCostsMap(OtherCostsDetailsDTO dto);

    @Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="costPerUnit", target="costPerUnit")
    @Mapping(source="auditCostName", target="auditFee.type")
    @Mapping(   source="units", target="auditFee.units",
                qualifiedBy=StringToUnitsMap.class)
    @Mapping(   source="annualQuantity", target="annualExpenses",
                qualifiedBy=AnnualExpenseMap.class)
    @BeanMapping(ignoreByDefault=true)
    Audit
    AuditMap(AuditFeeDetailsDTO dto);

    @Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="costPerUnit", target="costPerUnit")
    @Mapping(   source="annualQuantity", target="annualExpenses",
                qualifiedBy=AnnualExpenseMap.class)
    @BeanMapping(ignoreByDefault=true)
    RhdNonStipend
    RhdNonStipendCostMap(RhdNonStipendCostDetailsDTO dto);

    @Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="costPerUnit", target="costPerUnit")
    @Mapping(source="facilityCostName", target="facilityCost.name")
    @Mapping(   source="units", target="facilityCost.units",
                qualifiedBy=StringToUnitsMap.class)
    @Mapping(   source="annualQuantity", target="annualExpenses",
                qualifiedBy=AnnualExpenseMap.class)
    @BeanMapping(ignoreByDefault=true)
    Facility
    FacilityMap(FacilityCostDetailsDTO dto);

    @Mapping(source="id", target="id")
    @Mapping(source="type", target="type")
    @Mapping(source="description", target="description")
    @Mapping(source="inKindPercent", target="inKindPercent")
    @Mapping(source="costPerUnit", target="costPerUnit")
    @Mapping(source="organisation", target="organisation")
    @Mapping(   source="annualQuantity", target="annualExpenses",
                qualifiedBy=AnnualExpenseMap.class)
    @BeanMapping(ignoreByDefault=true)
    PartnerOrganisation
    PartnerOrganisationMap(PartnerOrganisationDetailsDTO dto);


    // =========================================================================
    // Methods
    // =========================================================================

    @UnitsToStringMap
    static String enumToString(Enum<?> e)
    {
        return e.toString();
    }

    @AnnualQuantityMap
    static
    Map<Integer, Double>
    annualExpenseMap(List<AnnualExpense> axs)
    {
        return axs
            .stream()
            .collect(Collectors.toMap(
                AnnualExpense::getYear,
                AnnualExpense::getUnits
            ));
    }

    @AnnualExpenseMap
    static
    List<AnnualExpense>
    annualExpenseMap(Map<Integer,Double> axs)
    {
        if (axs == null)
            return null;
        
        return axs.entrySet().stream()
            .map(e ->
                AnnualExpense.builder()
                    .year(e.getKey())
                    .units(e.getValue())
                    .build()
            )
            .collect(Collectors.toList());
    }

    @StringToUnitsMap
    static Units enumToString(String s)
    {
        return Enum.valueOf(Units.class, s);
    }

    // =========================================================================
    // Qualifiers
    // =========================================================================

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface AnnualQuantityMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface UnitsToStringMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface AnnualExpenseMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface StringToUnitsMap {}
}