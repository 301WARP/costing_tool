package au.edu.utas.costing_tool.Model;


// ============================================================================= 
// External Imports
// ============================================================================= 

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

// ============================================================================= 
// Project Imports
// ============================================================================= 

import au.edu.utas.costing_tool.Enums.ContractType;


@Data
@Entity
@Table(name="contract")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(   name="contract_type",
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

    @Column(name="contract_type",
            insertable=false,
            updatable=false)
    @Enumerated(value=EnumType.STRING)
    protected ContractType contractType;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="researcher_id", referencedColumnName="staff_id")
    @JsonBackReference
    @ToString.Exclude
    protected Researcher researcher;

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="contract")
    @JsonManagedReference
    protected List<Contribution> contributions;


    // ========================================================================= 
    // Constructors
    // ========================================================================= 

    public Contract()
    {
        this.setContributions(new ArrayList<Contribution>());
    }

    public Contract(ContractType type)
    {
        this();
        this.setContractType(type);
    }

    public Contract(Researcher researcher, ContractType type)
    {
        this(type);
        this.setResearcher(researcher);

        // Add contract to researcher
        this.getResearcher().addContract(this);
    }


    // ========================================================================= 
    // Methods
    // ========================================================================= 

    public abstract Double CostRate();

    public boolean addContribution(Contribution contribution)
    {
        return this.getContributions().add(contribution);
    }

    public boolean removeContribution(Contribution contribution)
    {
        return this.getContributions().remove(contribution);
    }
}