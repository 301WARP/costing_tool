package au.edu.utas.costing_tool.DTO.Expense;


// =============================================================================
// External Imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
//@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class AuditFeeDetailsDTO extends ExpenseDetailsDTO
{
    Long id;
    String type;
    String description;
    Double inKindPercent;
    Double costPerUnit;

    String auditCostName;
    String units;

    // <year, quantity>
    Map<Integer, Double> annualQuantity;
}