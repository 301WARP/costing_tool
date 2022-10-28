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
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue("LABORATORY_HIRE")
public class LaboratoryHire extends Expense
{
}