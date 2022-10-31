package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.Summary.SummaryDTO;
import au.edu.utas.costing_tool.Model.Contract.Casual;
import au.edu.utas.costing_tool.Model.Contract.NonCasual;
import au.edu.utas.costing_tool.Model.Contract.RHD;
import au.edu.utas.costing_tool.Model.Expense.Audit;
import au.edu.utas.costing_tool.Model.Expense.Consumables;
import au.edu.utas.costing_tool.Model.Expense.EquipmentPurchases;
import au.edu.utas.costing_tool.Model.Expense.ExternalContractor;
import au.edu.utas.costing_tool.Model.Expense.Facility;
import au.edu.utas.costing_tool.Model.Expense.LaboratoryHire;
import au.edu.utas.costing_tool.Model.Expense.OtherCosts;
import au.edu.utas.costing_tool.Model.Expense.PartnerOrganisation;
import au.edu.utas.costing_tool.Model.Expense.RhdNonStipend;
import au.edu.utas.costing_tool.Model.Expense.Travel;
import au.edu.utas.costing_tool.Model.Project.Project;


@Mapper
public interface SummaryMapper
{
    @Mapping(source="project", target="labour", qualifiedBy=LabourMap.class)
    @Mapping(source="project", target="expenses", qualifiedBy=ExpenseMap.class)
    @Mapping(source="profitMargin", target="profitMargin")
    @Mapping(source="utasCashContribution", target="utasCash")
    @Mapping(source="partnerCashContribution", target="partnerCash")
    public SummaryDTO map(Project project);

    // =============================================================================
    // Methods
    // =============================================================================

    @LabourMap
    default
    SummaryDTO.Labour
    labourMap(Project p)
    {
        SummaryDTO.LineItem nonCasual = SummaryDTO.LineItem.builder()
            .name("UTAS Staff (Excluding Casual)")
            .actualCost(p.labourActualCost(NonCasual.class))
            .inKindPercent(p.labourInKindPercent(NonCasual.class))
            .build();

        SummaryDTO.LineItem casual = SummaryDTO.LineItem.builder()
            .name("UTAS Casual Staff")
            .actualCost(p.labourActualCost(Casual.class))
            .inKindPercent(p.labourInKindPercent(Casual.class))
            .build();

        SummaryDTO.LineItem rhd = SummaryDTO.LineItem.builder()
            .name("RHD Stipends/Other")
            .actualCost(p.labourActualCost(RHD.class))
            .inKindPercent(p.labourInKindPercent(RHD.class))
            .build();

        return SummaryDTO.Labour.builder()
            .nonCasual(nonCasual)
            .casual(casual)
            .rhd(rhd)
            .build();
    }

    @ExpenseMap
    default
    SummaryDTO.Expenses
    expenseMap(Project p)
    {
        SummaryDTO.LineItem travel = SummaryDTO.LineItem.builder()
            .name("Travel")
            .actualCost(p.expensesActualCost(Travel.class))
            .inKindPercent(p.expenseInKindPercent(Travel.class))
            .build();

        SummaryDTO.LineItem laboratoryHire = SummaryDTO.LineItem.builder()
            .name("Facilities/Laboratory Hire")
            .actualCost(p.expensesActualCost(LaboratoryHire.class))
            .inKindPercent(p.expenseInKindPercent(LaboratoryHire.class))
            .build();

        SummaryDTO.LineItem consumables = SummaryDTO.LineItem.builder()
            .name("Consumables")
            .actualCost(p.expensesActualCost(Consumables.class))
            .inKindPercent(p.expenseInKindPercent(Consumables.class))
            .build();

        SummaryDTO.LineItem equipmentPurchases = SummaryDTO.LineItem.builder()
            .name("Equipment Purchases")
            .actualCost(p.expensesActualCost(EquipmentPurchases.class))
            .inKindPercent(p.expenseInKindPercent(EquipmentPurchases.class))
            .build();

        SummaryDTO.LineItem externalContractor = SummaryDTO.LineItem.builder()
            .name("External Contractors")
            .actualCost(p.expensesActualCost(ExternalContractor.class))
            .inKindPercent(p.expenseInKindPercent(ExternalContractor.class))
            .build();

        SummaryDTO.LineItem otherCosts = SummaryDTO.LineItem.builder()
            .name("Other Costs")
            .actualCost(p.expensesActualCost(OtherCosts.class))
            .inKindPercent(p.expenseInKindPercent(OtherCosts.class))
            .build();

        SummaryDTO.LineItem auditFees = SummaryDTO.LineItem.builder()
            .name("Audit Fees")
            .actualCost(p.expensesActualCost(Audit.class))
            .inKindPercent(p.expenseInKindPercent(Audit.class))
            .build();

        SummaryDTO.LineItem rhdNonStipendCosts = SummaryDTO.LineItem.builder()
            .name("RHD Non-Stipend Costs")
            .actualCost(p.expensesActualCost(RhdNonStipend.class))
            .inKindPercent(p.expenseInKindPercent(RhdNonStipend.class))
            .build();

        SummaryDTO.LineItem facilityCosts = SummaryDTO.LineItem.builder()
            .name("Facility Costs")
            .actualCost(p.expensesActualCost(Facility.class))
            .inKindPercent(p.expenseInKindPercent(Facility.class))
            .build();

        SummaryDTO.LineItem partnerOrganisation = SummaryDTO.LineItem.builder()
            .name("Partner Organisation Costs")
            .actualCost(p.expensesActualCost(PartnerOrganisation.class))
            .inKindPercent(p.expenseInKindPercent(PartnerOrganisation.class))
            .build();

        return SummaryDTO.Expenses.builder()
            .travel(travel)
            .laboratoryHire(laboratoryHire)
            .consumables(consumables)
            .equipmentPurchases(equipmentPurchases)
            .externalContractor(externalContractor)
            .otherCosts(otherCosts)
            .auditFees(auditFees)
            .rhdNonStipendCosts(rhdNonStipendCosts)
            .facilityCosts(facilityCosts)
            .partnerOrganisation(partnerOrganisation)
            .build();
    }
    // =============================================================================
    // Qualifiers
    // =============================================================================

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface LabourMap {}

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ExpenseMap {}
}