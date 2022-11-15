package au.edu.utas.costing_tool.Model.Contribution;


// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// =============================================================================
// Project imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.Contribution.SalaryOnCostRateType;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="salary_on_cost")
@IdClass(value=SalaryOnCostRateId.class)
public class SalaryOnCostRate
{
    @Id
    @Column(name="type")
    @Enumerated(value=EnumType.STRING)
    private SalaryOnCostRateType type;

    @Id
    @Column(name="year")
    private Integer year; 

    @Column(name="percent")
    private Double rate;
}