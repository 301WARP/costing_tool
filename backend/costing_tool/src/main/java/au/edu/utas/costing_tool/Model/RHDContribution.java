package au.edu.utas.costing_tool.Model;

// ============================================================================= 
// External Imports
// ============================================================================= 

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="contribution")
@DiscriminatorValue("RHD")
public class RHDContribution extends AnnualContribution
{
    // ========================================================================= 
    // Properties
    // ========================================================================= 

    @Column(name="fte")
    private Double fte; 
    public Double getFTE() {return this.fte;}
    public void setFTE(Double fte) {this.fte = fte;}

    
    // ========================================================================= 
    // Constructors
    // ========================================================================= 

    RHDContribution() {}

    RHDContribution(Double fte)
    {
        super();
        this.setFTE(fte);
    }

    RHDContribution(Contribution contribution, Double fte)
    {
        super(contribution);
        this.setFTE(fte);
    }

    // ========================================================================= 
    // Methods
    // ========================================================================= 

    public Double Price()
    {
        Contract c = this.id.getContract();

        // TODO(Andrew): Should be impossible; throw exception
        if (!(c instanceof RHD))
            return null;

        // TODO(Andrew): equation is more complicated than this should involve
        //               wage adjustment, salary on cost rate, etc.
        return ((RHD)c).getAnnualSalary() * this.getFTE();
    }
}
