package au.edu.utas.costing_tool.Model;

// ============================================================================= 
// External Imports
// ============================================================================= 

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="contribution")
public class Contribution
{
    // ========================================================================= 
    // Properties
    // ========================================================================= 

    @EmbeddedId
    private ContributionID id;

    @Column(name="role")
    private String role; 
    public String getRole() {return this.role;}
    public void setRole(String role) {this.role = role;}

    @Column(name="in_kind_%")
    private Double inKindPercent; 
    public Double getInKindPercent() {return this.inKindPercent;}
    public void setInKindPercent(Double inKindPercent)
        {this.inKindPercent = inKindPercent;}

    // TODO(Andrew): Does this work?
    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="contribution")
    @JsonManagedReference
    private List<AnnualContribution> annualContributions;
    public List<AnnualContribution> getAnnualContributions()
        {return this.annualContributions;}
    public void setAnnualContributions(List<AnnualContribution> contributions)
        {this.annualContributions = contributions;}
    
    
    // ========================================================================= 
    // Constructors
    // ========================================================================= 

    public Contribution() {}

    public Contribution(String role, Double inKindPercent)
    {
        this.setRole(role);
        this.setInKindPercent(inKindPercent);
    }

    public Contribution(String role,
                        Double inKindPercent,
                        List<AnnualContribution> contributions)
    {
        this.setRole(role);
        this.setInKindPercent(inKindPercent);
        this.setAnnualContributions(contributions);
    }


    // ========================================================================= 
    // Methods
    // ========================================================================= 

    public Double Price()
    {
        return this.annualContributions
            .stream()
            .mapToDouble(c -> c.Price())
            .reduce(0.0, (total, p) -> total + p);
    }

    public Double InKindDollar() 
    {
        return this.Price() * this.getInKindPercent();
    }

    public boolean addAnnualContribution(AnnualContribution contribution)
    {
        return this.getAnnualContributions().add(contribution);
    }

    public boolean removeAnnualContribution(AnnualContribution contribution)
    {
        return this.getAnnualContributions().remove(contribution);
    }
}