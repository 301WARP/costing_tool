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
@DiscriminatorValue("CASUAL")
public class CasualContribution extends AnnualContribution
{
    // ========================================================================= 
    // Properties
    // ========================================================================= 

    @Column(name="hours")
    private Double hours; 
    public Double getHours() {return this.hours;}
    public void setHours(Double hours) {this.hours = hours;}

    
    // ========================================================================= 
    // Constructors
    // ========================================================================= 

    public CasualContribution() {super();}

    public CasualContribution(Double hours)
    {
        super();
        this.setHours(hours);
    }

    public CasualContribution(Contribution contribution, Double hours)
    {
        super(contribution);
        this.setHours(hours);
    }


    // ========================================================================= 
    // Methods
    // ========================================================================= 

    public Double Price()
    {
        Contract c = this.id.getContract();

        // TODO(Andrew): Should be impossible; throw exception
        if (!(c instanceof Casual))
            return null;

        // TODO(Andrew): equation is more complicated than this should involve
        //               wage adjustment, salary on cost rate, etc.
        return ((Casual)c).getHourlyRate() * this.getHours();
    }
}
