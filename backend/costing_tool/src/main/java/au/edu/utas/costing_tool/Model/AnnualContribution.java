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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Long contractID;

    @Id
    @Column(name="project_id")
    private Long projectID;

    @Id
    @Column(name="year")
    private Integer year;

    public AnnualContributionID getId()
    {
        if (   this.getContractID() == null
            || this.getProjectID() == null
            || this.getYear() == null)
            return null;

        return new AnnualContributionID(this.getContractID(),
                                        this.getProjectID(),
                                        this.getYear());
    }


    @ManyToOne
    @MapsId
    @JoinColumns({
        @JoinColumn(name="contract_id", referencedColumnName="contract_id"),
        @JoinColumn(name="project_id", referencedColumnName="project_id")
    })
    @ToString.Exclude
    @JsonBackReference
    protected Contribution contribution;
    public void setContribution(Contribution contribution)
    {
        this.setContractID(contribution.contractID);
        this.setProjectID(contribution.projectID);
        this.contribution = contribution;
    }
    
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

    public AnnualContribution(Long contractID, Long projectID, Integer year)
    {
        this.setContractID(contractID);
        this.setProjectID(projectID);

        // TODO(Andrew): Consider setting contribution here somehow

        this.setYear(year);
    }

    public AnnualContribution(  Long contractID,
                                Long projectID,
                                Integer year,
                                Double units)
    {
        this.setContractID(contractID);
        this.setProjectID(projectID);

        // TODO(Andrew): Consider setting contract here somehow

        this.setYear(year);
        this.setUnits(units);
    }

    public AnnualContribution(  Contract contract,
                                Project project,
                                Integer year,
                                Double units)
    {
        this.setContractID(contract.getId());
        this.setProjectID(project.getId());
        this.setYear(year);
        this.setUnits(units);
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