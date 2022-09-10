package au.edu.utas.costing_tool.Model;


// ============================================================================= 
// External Imports
// ============================================================================= 

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void setContract(Contract contract)
    {
        this.contract = contract;

        Long contractID = contract != null ? contract.getId() : null;

        this.setContractID(contractID);
        
        this.getAnnualContributions()
            .stream()
            .forEach(a -> a.setContractID(contractID));
    }

    @ManyToOne
    @MapsId("projectID")
    @JsonBackReference
    private Project project;
    public void setProject(Project project)
    {
        this.project = project;

        Long projectID = project != null ? project.getID() : null;

        this.setProjectID(projectID);
        
        this.getAnnualContributions()
            .stream()
            .forEach(a -> a.setProjectID(projectID));
    }

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="contribution",
                orphanRemoval=true)
    @JsonManagedReference
    // TODO(Andrew): final?
    private List<AnnualContribution> annualContributions;
    
    
    // ========================================================================= 
    // Constructors
    // ========================================================================= 

    public Contribution()
    {
        this.setAnnualContributions(new ArrayList<AnnualContribution>());
    }

    public Contribution(Long contractID, Long projectID)
    {
        this();
        this.setContractID(contractID);
        this.setProjectID(projectID);
    }

    public Contribution(Long contractID, Long projectID,
                        String role, Double inKindPercent)
    {
        this(contractID, projectID);
        this.setRole(role);
        this.setInKindPercent(inKindPercent);
    }


    // ========================================================================= 
    // Methods
    // ========================================================================= 

    public boolean hasAnnualContribution(Integer year)
    {
        List<AnnualContribution> ac = this.getAnnualContributions();

        if (year == null || ac == null || ac.isEmpty())
            return false;
        
        return this
            .getAnnualContributions()
            .stream()
            .anyMatch(a -> a.getYear().equals(year));
    }

    public AnnualContribution findAnnualContribution(AnnualContribution ac)
    {
        if (ac == null
                || this.getAnnualContributions() == null
                || this.getAnnualContributions().isEmpty())
            return null;

        return this .getAnnualContributions()
                    .stream()
                    .filter(a -> a.getId().equals(ac.getId()))
                    .findFirst()
                    .orElse(null);
    }

    public AnnualContribution findAnnualContributionById(AnnualContributionID id)
    {
        if (id == null
                || this.getAnnualContributions() == null
                || this.getAnnualContributions().isEmpty())
            return null;

        return this .getAnnualContributions()
                    .stream()
                    .filter(a -> a.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }

    public AnnualContribution findAnnualContributionByYear(Integer year)
    {
        if (year == null
                || this.getAnnualContributions() == null
                || this.getAnnualContributions().isEmpty())
            return null;

        return this .getAnnualContributions()
                    .stream()
                    .filter(a -> a.getYear().equals(year))
                    .findFirst()
                    .orElse(null);
    }

    public
    List<AnnualContribution>
    findAnnualContributions(List<AnnualContribution> ac)
    {
        if (ac == null
                || ac.isEmpty()
                || this.getAnnualContributions() == null
                || this.getAnnualContributions().isEmpty())
            return null;

        return ac
                .stream()
                .filter(a -> this.findAnnualContribution(a) != null)
                .collect(Collectors.toList());
    }

    public
    List<AnnualContribution>
    findAnnualContributionsById(List<AnnualContributionID> id)
    {
        if (id == null
                || id.isEmpty()
                || this.getAnnualContributions() == null
                || this.getAnnualContributions().isEmpty())
            return null;

        return id
                .stream()
                .map(a -> this.findAnnualContributionById(a))
                .filter(a -> a != null)
                .collect(Collectors.toList());
    }

    public void clearAnnualContributions()
    {
        this.getAnnualContributions().clear();
    }

    public boolean addAnnualContribution(AnnualContribution ac)
    {
        if (ac != null && this.getAnnualContributions().add(ac)) {
            ac.setContribution(this);
            return true;
        }
            
        return false;
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