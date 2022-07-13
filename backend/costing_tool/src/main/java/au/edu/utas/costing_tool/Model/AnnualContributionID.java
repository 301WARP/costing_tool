package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.util.Objects;


public class AnnualContributionID extends ContributionID
{
    // =========================================================================
    // Properties
    // =========================================================================

    private static final long serialVersionUID = 1L;

    private Integer year;
    public Integer getYear() {return this.year;}
    public void setYear(Integer year) {this.year = year;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public AnnualContributionID() {super();}

    public AnnualContributionID(Long contractID, Long projectID, Integer year)
    {
        super(contractID, projectID);
        this.setYear(year);
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof AnnualContributionID))
            return false;

        AnnualContributionID id = (AnnualContributionID) o;

        return super.equals(o)
            && Objects.equals(this.getYear(), id.getYear());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.getContractID(),
                            this.getProjectID(),
                            this.getYear());
    }
}