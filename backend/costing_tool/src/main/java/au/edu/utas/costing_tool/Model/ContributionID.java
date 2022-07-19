package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.io.Serializable;
import java.util.Objects;


public class ContributionID implements Serializable
{
    // =========================================================================
    // Properties
    // =========================================================================

    private static final long serialVersionUID = 1L;

    protected Long contractID;
    public Long getContractID() {return this.contractID;}
    public void setContractID(Long id) {this.contractID = id;}

    protected Long projectID;
    public Long getProjectID() {return this.projectID;}
    public void setProjectID(Long id) {this.projectID = id;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public ContributionID() {}

    public ContributionID(Long contractID, Long projectID)
    {
        this.setContractID(contractID);
        this.setProjectID(projectID);
    }


    // =========================================================================
    // Methods
    // =========================================================================

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof ContributionID))
            return false;

        ContributionID id = (ContributionID) o;

        return Objects.equals(this.getContractID(), id.getContractID())
            && Objects.equals(this.getProjectID(), id.getProjectID());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.getContractID(), this.getProjectID());
    }
}