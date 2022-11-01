package au.edu.utas.costing_tool.Model.Contract;


// =============================================================================
// External imports
// =============================================================================

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@NoArgsConstructor
@Entity
public abstract class Staff extends Contract
{
}