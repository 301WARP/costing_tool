package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import java.io.Serializable;
//import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AnnualExpenseID implements Serializable
{
    // =========================================================================
    // Properties
    // =========================================================================

    protected Long expenseID;

    protected Integer year;


    // =========================================================================
    // Constructors
    // =========================================================================


    public AnnualExpenseID(Long expenseID, Integer year)
    {
        this.setExpenseID(expenseID);
        this.setYear(year);
    }


    // =========================================================================
    // Methods
    // =========================================================================

    /*
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof AnnualExpenseID))
            return false;

        AnnualExpenseID id = (AnnualExpenseID) o;

        return Objects.equals(this.getExpenseID(), id.getExpenseID())
            && Objects.equals(this.getYear(), id.getYear());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.getExpenseID(), this.getYear());
    }
    */
}