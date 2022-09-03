package au.edu.utas.costing_tool.Model;


// ============================================================================= 
// External Imports
// ============================================================================= 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;


@Data
@Entity
@Table(name="annual_contribution")
@IdClass(value=AnnualContributionID.class)
@ToString(exclude="contribution")
public class AnnualContribution
{
    // ========================================================================= 
    // Properties
    // ========================================================================= 

    @Id
    @Column(name="contract_id")
    private Long contractID;

    @Id
    @Column(name="project_id")
    private Long projectID;

    @Id
    @Column(name="year")
    private Integer year;

    public AnnualContributionID getId()
    {
        return new AnnualContributionID(this.contractID,
                                        this.projectID,
                                        this.year);
    }


    @ManyToOne
    @MapsId
    @JoinColumns({
        @JoinColumn(name="contract_id", referencedColumnName="contract_id"),
        @JoinColumn(name="project_id", referencedColumnName="project_id")
    })
    @JsonBackReference
    protected Contribution contribution;
    public void setContribution(Contribution contribution)
    {
        this.setContractID(contribution.contractID);
        this.setProjectID(contribution.projectID);
        this.contribution = contribution;
    }
    /*
    public Contribution getContribution()
    {
        if (this.contribution != null)
            return this.contribution;
        
        // TODO(Andrew): fetch contribution from DB?
        return null;
    }
    */
    
    @Column(name="units")
    protected Double units;

    // Virtual fte
    @JsonIgnore
    public Double getFTE() {return this.units;}
    public void setFTE(Double fte) {this.units = fte;}

    // Virtual hours
    @JsonIgnore
    public Double getHours() {return this.units;}
    public void setHours(Double hours) {this.units = hours;}


    // ========================================================================= 
    // Constructors
    // ========================================================================= 

    public AnnualContribution() {}

    public AnnualContribution(Long contractID, Long projectID, Integer year)
    {
        this.setContractID(contractID);
        this.setProjectID(projectID);

        // TODO(Andrew): Consider setting contract here somehow

        this.setYear(year);
    }

    public AnnualContribution(Contribution contribution, Integer year)
    {
        this.setContribution(contribution);
        this.setYear(year);

        // Update contribution with this annual contribution
        this.getContribution().addAnnualContribution(this);
    }

    public AnnualContribution(Integer year, Double units)
    {
        this.setYear(year);
        this.setUnits(units);
    }
}