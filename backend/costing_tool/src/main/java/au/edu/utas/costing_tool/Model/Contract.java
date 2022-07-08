package au.edu.utas.costing_tool.Model;


// ============================================================================= 
// External Imports
// ============================================================================= 

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


// ============================================================================= 
// Project Imports
// ============================================================================= 

import au.edu.utas.costing_tool.Enums.ContractType;


@Entity
@Table(name="contract")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(   name="staff_type",
                        discriminatorType=DiscriminatorType.STRING)
public abstract class Contract
{
    // ========================================================================= 
    // Properties
    // ========================================================================= 

    @Id
    @GeneratedValue
    @Column(name="id")
    protected Long id;
    public Long getID() {return this.id;}
    public void setID(Long id) {this.id = id;}

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="researcher_id")
    @JsonBackReference
    protected Researcher researcher;
    public Researcher getResearcher() {return this.researcher;}
    public void setResearcher(Researcher r) {this.researcher = r;}

    @Column(name="staff_type")
    protected ContractType type;
    public ContractType getType() {return this.type;}
    public void setType(ContractType type) {this.type = type;}

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="id.contract")
    protected List<Contribution> contributions;
    public List<Contribution> getContributions() {return this.contributions;}
    public void setContributions(List<Contribution> contributions)
        {this.contributions = contributions;}


    // TODO(Andrew): How to do this?
    //private Dictionary<Year, Double> FTE;


    // ========================================================================= 
    // Constructors
    // ========================================================================= 

    public Contract() {}

    public Contract(Researcher researcher, ContractType type)
    {
        this.setResearcher(researcher);
        this.setType(type);

        // Add contract to researcher
        this.getResearcher().addContract(this);
    }


    // ========================================================================= 
    // Methods
    // ========================================================================= 

    public boolean addContribution(Contribution contribution)
    {
        return this.getContributions().add(contribution);
    }

    public boolean removeContribution(Contribution contribution)
    {
        return this.getContributions().remove(contribution);
    }
}