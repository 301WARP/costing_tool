package au.edu.utas.costing_tool.Model.Contract;


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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


// ============================================================================= 
// Project Imports
// ============================================================================= 

import au.edu.utas.costing_tool.Enums.Contract.ContractType;
import au.edu.utas.costing_tool.Model.Contribution.Contribution;
import au.edu.utas.costing_tool.Model.Entity.Unit;
import au.edu.utas.costing_tool.Model.Researcher.Researcher;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="researcher_id", referencedColumnName="staff_id")
    @JsonBackReference
    @ToString.Exclude
    protected Researcher researcher;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unit_id", referencedColumnName="id")
    @JsonBackReference
    @ToString.Exclude
    protected Unit unit;

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="contract")
    @JsonManagedReference
    protected List<Contribution> contributions = new ArrayList<Contribution>();


    // ========================================================================= 
    // Methods
    // ========================================================================= 

    //public abstract Double CostRate();

    public boolean addContribution(Contribution contribution)
    {
        return this.getContributions().add(contribution);
    }

    public boolean removeContribution(Contribution contribution)
    {
        return this.getContributions().remove(contribution);
    }
}