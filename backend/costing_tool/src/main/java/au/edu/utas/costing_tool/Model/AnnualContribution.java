package au.edu.utas.costing_tool.Model;

// ============================================================================= 
// External Imports
// ============================================================================= 

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="contribution")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(   name="contribution_type",
                        discriminatorType=DiscriminatorType.INTEGER)
public abstract class AnnualContribution
{
    // ========================================================================= 
    // Properties
    // ========================================================================= 

    @EmbeddedId
    protected AnnualContributionID id;
    public AnnualContributionID getID() {return this.id;}
    public void setID(AnnualContributionID id) {this.id = id;}

    // TODO(Andrew): Does this work?
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="contract_id"),
        @JoinColumn(name="project_id")
    })
    protected Contribution contribution;
    public Contribution getContribution() {return this.contribution;}
    public void setContribution(Contribution contribution)
        {this.contribution = contribution;}
    
    // ========================================================================= 
    // Constructors
    // ========================================================================= 

    public AnnualContribution() {}

    public AnnualContribution(Contribution contribution)
    {
        this.setContribution(contribution);

        // Update contribution with this annual cotnribution
        this.getContribution().addAnnualContribution(this);
    }


    // ========================================================================= 
    // Methods
    // ========================================================================= 

    public abstract Double Price();

    public Double InKindDollar() 
    {
        return this.Price() * this.contribution.getInKindPercent();
    }

    public Double CashIncome() 
    {
        return this.Price() - this.InKindDollar();
    }
}
