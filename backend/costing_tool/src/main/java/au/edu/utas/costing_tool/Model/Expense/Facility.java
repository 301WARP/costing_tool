package au.edu.utas.costing_tool.Model.Expense;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@Entity
@DiscriminatorValue("FACILITY_COSTS")
public class Facility extends Expense
{
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="facility_cost_id", referencedColumnName="id")
    FacilityCost facilityCost;

    @Override
    public Double getCostPerUnit() {
        return this.facilityCost.getCostPerUnit();
    }
}