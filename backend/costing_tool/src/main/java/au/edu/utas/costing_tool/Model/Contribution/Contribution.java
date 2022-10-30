package au.edu.utas.costing_tool.Model.Contribution;


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

import au.edu.utas.costing_tool.Model.Contract.Casual;
import au.edu.utas.costing_tool.Model.Contract.Contract;
import au.edu.utas.costing_tool.Model.Contract.NonCasual;
import au.edu.utas.costing_tool.Model.Contract.RHD;
import au.edu.utas.costing_tool.Model.Project.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@AllArgsConstructor
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

    @Column(name="`wage_adjustment`")
    private Double wageAdjustment; 

    @Column(name="`on_cost_rate`")
    private Double onCostRate; 

    @ManyToOne
    @MapsId(value="contractID")
    @JsonBackReference
    @ToString.Exclude
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
    @ToString.Exclude
    private Project project;
    public void setProject(Project project)
    {
        this.project = project;

        Long projectID = project == null ? null : project.getId();

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
        Double rate = this.getOnCostRate(); 

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
        if (ac == null)
            return null;

        Double onCost = this.getOnCostRate(); 
        Double fte = ac.getFTE();
        Double hours = ac.getHours();

        // TODO(Andrew): throw error?
        if (onCost == null || fte == null || hours == null)
            return null;

        Contract contract = this.getContract();

        // TODO(Andrew): throw error?
        if (contract == null)
            return null;

        if (contract instanceof NonCasual) {
            Double salary = ((NonCasual)contract).getStartingSalary();

            if (salary == null)
                return null;

            return salary * onCost * fte / 100.0;
        }
        else if (contract instanceof RHD)  {
            Double salary = ((RHD)contract).getAnnualSalary();

            if (salary == null)
                return null;

            return salary * fte / 100.0;
        }
        else if (contract instanceof Casual) {
            Double hourlyRate = ((Casual)contract).getHourlyRate();

            if (hourlyRate == null)
                return null;

            return hourlyRate * onCost * hours;
        }
        // Unkown contract
        // TODO(Andrew): throw error?
        else
            return null;
    }

    public Double Price()
    {
        if (this.getAnnualContributions() == null)
            return 0.0;

        return this.getAnnualContributions()
            .stream()
            .map(this::AnnualPrice)
            .filter(p -> p != null)
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