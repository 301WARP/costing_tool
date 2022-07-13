package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.time.LocalDate;

import javax.persistence.Embeddable;


@Embeddable
public class CollegeEndorsement
{
    // =========================================================================
    // Properties
    // =========================================================================

    private Boolean endorsement;
    public Boolean getEndorsement() {return this.endorsement;}
    public void setEndorsement(Boolean endorsed) {this.endorsement = endorsed;}

    private LocalDate date;
    public LocalDate getDate() {return this.date;}
    public void setDate(LocalDate date) {this.date = date;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public CollegeEndorsement() {}

    public CollegeEndorsement(  Boolean endorsed,
                                LocalDate date)
    {
        this.setEndorsement(endorsed);
        this.setDate(date);
    }
}