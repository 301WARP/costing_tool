package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.Facility;
import au.edu.utas.costing_tool.Enums.TimeUnit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue("FACILITY_HIRE")
public class FacilityHire extends Expense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="facility")
    @Enumerated(value=EnumType.STRING)
    private Facility facility;

    @Column(name="time_unit")
    @Enumerated(value=EnumType.STRING)
    private TimeUnit timeUnit;


    /*
    // =========================================================================
    // Constructors
    // =========================================================================

    public FacilityHire(Project project,
                        ExpenseType type,
                        Double costPerUnit,
                        Double inKindPercent,
                        Facility facility,
                        TimeUnit timeUnit)
    {
        super(project, type, costPerUnit, inKindPercent);

        this.setFacility(facility);
        this.setTimeUnit(timeUnit);
    }
    */
}