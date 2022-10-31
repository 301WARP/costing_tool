package au.edu.utas.costing_tool.DTO.Summary;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class SummaryDTO
{
    Labour labour;
    Expenses expenses;
    Double profitMargin;
    Double utasCash;
    Double partnerCash;
    

    // =========================================================================
    // Nested Classes
    // =========================================================================

    @Data
    @Builder
    public static class LineItem
    {
        String name;
        Double actualCost;
        Double inKindPercent;
    }


    @Data
    @Builder
    public static class Labour
    {
        LineItem nonCasual;
        LineItem casual;
        LineItem rhd;
    }

    @Data
    @Builder
    public static class Expenses
    {
        LineItem travel;
        LineItem laboratoryHire;
        LineItem consumables;
        LineItem equipmentPurchases;
        LineItem externalContractor;
        LineItem otherCosts;
        LineItem auditFees;
        LineItem rhdNonStipendCosts;
        LineItem facilityCosts;
        LineItem partnerOrganisation;
    }
}