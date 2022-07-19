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


@Entity
@Table(name="annual_contribution")
@IdClass(value=AnnualContributionID.class)
public class AnnualContribution
{
    // ========================================================================= 
    // Properties
    // ========================================================================= 

    @Id
    @Column(name="contract_id")
    protected Long contractID;
    public Long getContractID() {return this.contractID;}
    public void setContractID(Long id) {this.contractID = id;}

    @Id
    @Column(name="project_id")
    protected Long projectID;
    public Long getProjectID() {return this.projectID;}
    public void setProjectID(Long id) {this.projectID = id;}

    @Id
    @Column(name="year")
    private Integer year;
    public Integer getYear() {return this.year;}
    public void setYear(Integer year) {this.year = year;}

    @ManyToOne
    @MapsId
    @JoinColumns({
        @JoinColumn(name="contract_id", referencedColumnName="contract_id"),
        @JoinColumn(name="project_id", referencedColumnName="project_id")
    })
    @JsonBackReference
    protected Contribution contribution;
    public Contribution getContribution() {return this.contribution;}
    public void setContribution(Contribution contribution)
        {this.contribution = contribution;}
    
    @Column(name="units")
    protected Double units;
    public Double getUnits() {return this.units;}
    public void setUnits(Double units) {this.units = units;}

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

    public AnnualContribution(Contribution contribution)
    {
        this.setContribution(contribution);

        // Update contribution with this annual contribution
        this.getContribution().addAnnualContribution(this);
    }
}