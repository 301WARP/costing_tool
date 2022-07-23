package au.edu.utas.costing_tool.Model;


// ============================================================================= 
// External Imports
// ============================================================================= 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Data
@Entity
@Table(name="contribution")
@IdClass(value=ContributionID.class)
public class Contribution
{
    // ========================================================================= 
    // Properties
    // ========================================================================= 

    @Id
    @Column(name="contract_id")
    protected Long contractID;

    @Id
    @Column(name="project_id")
    protected Long projectID;

    @Column(name="role")
    private String role; 

    @Column(name="`in_kind_%`")
    private Double inKindPercent; 

    @ManyToOne
    @MapsId(value="contractID")
    @JsonBackReference
    private Contract contract;

    @ManyToOne
    @MapsId("projectID")
    @JsonBackReference
    private Project project;

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="contribution")
    @JsonManagedReference
    private List<AnnualContribution> annualContributions;
    
    
    // ========================================================================= 
    // Constructors
    // ========================================================================= 

    public Contribution()
    {
        this.setAnnualContributions(new ArrayList<AnnualContribution>());
    }

    public Contribution(String role, Double inKindPercent)
    {
        this();
        this.setRole(role);
        this.setInKindPercent(inKindPercent);
    }


    // ========================================================================= 
    // Methods
    // ========================================================================= 

    public boolean addAnnualContribution(AnnualContribution contribution)
    {
        return this.getAnnualContributions().add(contribution);
    }

    public boolean removeAnnualContribution(AnnualContribution contribution)
    {
        return this.getAnnualContributions().remove(contribution);
    }

    public Double AnnualPrice(Integer year)
    {
        Contract contract = this.getContract();

        if (contract == null)
            return null;

        Double rate = contract.CostRate(); 

        if (rate == null)
            return null;

        // Assume there is only one annual contribution per year
        double units =  this.getAnnualContributions()
            .stream()
            .filter(ac -> year.equals(ac.getYear()))
            .mapToDouble(ac -> ac.getUnits())
            .findFirst()
            .orElse(0.0);

        return rate * units;
    }

    public Double AnnualPrice(AnnualContribution ac)
    {
        Contract contract = this.getContract();

        if (ac == null || contract == null)
            return null;

        Double rate = contract.CostRate(); 
        Double fte = ac.getFTE();
        Double hours = ac.getHours();

        if (rate == null || fte == null || hours == null)
            return null;

        if (contract instanceof NonCasual || contract instanceof RHD)
            return rate * fte / 100.0;
        else if (contract instanceof Casual)
            return rate * hours;
        // Unkown contract
        else
            return null;
    }

    public Double Price()
    {
        return this.getAnnualContributions()
            .stream()
            .mapToDouble(this::AnnualPrice)
            .reduce(0.0, (total, p) -> total + p);
    }

    public Double InKindDollar() 
    {
        Double price = this.Price();
        Double inKindPercent = this.getInKindPercent();

        if (price == null || inKindPercent == null)
            return null;

        return price * inKindPercent / 100.0;
    }

    public Double cashIncome() 
    {
        Double price = this.Price();
        Double inKindDollar = this.InKindDollar();

        if (price == null || inKindDollar == null)
            return null;

        return price - inKindDollar;
    }
}