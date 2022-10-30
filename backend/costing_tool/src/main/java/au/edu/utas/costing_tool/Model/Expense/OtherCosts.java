package au.edu.utas.costing_tool.Model.Expense;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@Entity
@DiscriminatorValue("OTHER_COSTS")
public class OtherCosts extends Expense
{
}