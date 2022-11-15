package au.edu.utas.costing_tool.Model.Contribution;


import java.io.Serializable;

import au.edu.utas.costing_tool.Enums.Contribution.SalaryOnCostRateType;

// =============================================================================
// External imports
// =============================================================================

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SalaryOnCostRateId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private SalaryOnCostRateType type;
    private Integer year;
}