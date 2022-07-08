package au.edu.utas.costing_tool.Model;

// =============================================================================
// External imports
// =============================================================================

import java.time.Year;

import javax.persistence.Embeddable;


@Embeddable
public class AnnualContributionID extends ContributionID
{
    // =========================================================================
    // Properties
    // =========================================================================

    private Year year;
    public Year getYear() {return this.year;}
    public void setYear(Year year) {this.year = year;}


    // =========================================================================
    // Constructors
    // =========================================================================

    AnnualContributionID() {super();}

    AnnualContributionID(Contract contract, Project project, Year year)
    {
        super(contract, project);
        this.setYear(year);
    }
}
