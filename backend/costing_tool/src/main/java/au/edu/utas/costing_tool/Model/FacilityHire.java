package au.edu.utas.costing_tool.Model;

// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


// =============================================================================
// Enums
// =============================================================================

import au.edu.utas.costing_tool.Enums.Facility;
import au.edu.utas.costing_tool.Enums.TimeUnit;
import au.edu.utas.costing_tool.Enums.ExpenseType;


@Entity
@Table(name="expense")
@DiscriminatorValue("FACILITY_HIRE")
public class FacilityHire extends Expense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="facility")
    private Facility facility;
    public Facility getFacility() {return this.facility;}
    public void setFacility(Facility facility) {this.facility = facility;}

    @Column(name="time_unit")
    private TimeUnit timeUnit;
    public TimeUnit getTimeUnit() {return this.timeUnit;}
    public void setTimeUnit(TimeUnit timeUnit) {this.timeUnit = timeUnit;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public FacilityHire() {super();}

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
}
