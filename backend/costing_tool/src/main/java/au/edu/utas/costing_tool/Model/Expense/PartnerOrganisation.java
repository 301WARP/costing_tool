package au.edu.utas.costing_tool.Model.Expense;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Column;
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
@DiscriminatorValue("PARTNER_ORGANISATION")
public class PartnerOrganisation extends Expense
{
    @Column(name="organisation")
    private String organisation;
}