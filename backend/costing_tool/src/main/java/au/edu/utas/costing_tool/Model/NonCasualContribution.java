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
@DiscriminatorValue("NON_CASUAL")
public class NonCasualContribution extends AnnualContribution
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

    NonCasualContribution() {super();}

    NonCasualContribution(Double fte)
    {
        super();
        this.setFTE(fte);
    }

    NonCasualContribution(Contribution contribution, Double fte)
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
        if (!(c instanceof NonCasual))
            return null;

        // TODO(Andrew): equation is more complicated than this should involve
        //               wage adjustment, salary on cost rate, etc.
        return ((NonCasual)c).getStartingSalary() * this.getFTE();
    }
}
