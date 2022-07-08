package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ExpenseType;
import au.edu.utas.costing_tool.Enums.Organisation;


@Entity
@Table(name="expense")
@DiscriminatorValue("PARTNER_ORGANISATION")
public class PartnerOrganisation extends Expense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="organisation")
    private Organisation organisation;
    public Organisation getOrganisation() {return this.organisation;}
    public void setOrganisation(Organisation organisation)
        {this.organisation = organisation;}

    
    // =========================================================================
    // Constructors
    // =========================================================================

    public PartnerOrganisation() {super();}

    public PartnerOrganisation( Project project,
                                ExpenseType type,
                                Double costPerUnit,
                                Double inKindPercent,
                                Organisation organisation)
    {
        super(project, type, costPerUnit, inKindPercent);

        this.setOrganisation(organisation);
    }
}
