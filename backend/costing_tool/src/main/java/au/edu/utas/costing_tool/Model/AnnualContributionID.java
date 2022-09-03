package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class AnnualContributionID extends ContributionID
{
    // =========================================================================
    // Properties
    // =========================================================================

    private static final long serialVersionUID = 1L;

    private Integer year;


    // =========================================================================
    // Constructors
    // =========================================================================

    public AnnualContributionID() {super();}

    public AnnualContributionID(Long contractID, Long projectID, Integer year)
    {
        super(contractID, projectID);
        this.setYear(year);
    }
}