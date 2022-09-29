package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ExpenseType;
import au.edu.utas.costing_tool.Enums.Organisation;


@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity
@DiscriminatorValue("PARTNER_ORGANISATION")
public class PartnerOrganisation extends Expense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="organisation")
    @Enumerated(value=EnumType.STRING)
    private Organisation organisation;

    
    /*
    // =========================================================================
    // Constructors
    // =========================================================================


    public PartnerOrganisation( Project project,
                                ExpenseType type,
                                Double costPerUnit,
                                Double inKindPercent,
                                Organisation organisation)
    {
        super(project, type, costPerUnit, inKindPercent);

        this.setOrganisation(organisation);
    }
    */
}