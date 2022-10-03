package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.Organisation;


@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("PARTNER_ORGANISATION")
public class PartnerOrganisation extends Expense
{
    @Column(name="organisation")
    @Enumerated(value=EnumType.STRING)
    private Organisation organisation;
}