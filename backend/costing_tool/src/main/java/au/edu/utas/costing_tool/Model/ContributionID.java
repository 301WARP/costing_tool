package au.edu.utas.costing_tool.Model;

// =============================================================================
// External imports
// =============================================================================

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
public class ContributionID implements Serializable
{
    // =========================================================================
    // Properties
    // =========================================================================

    // TODO(Andrew): Just use ids?
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="contract_id")
    @JsonBackReference
    protected Contract contract;
    public Contract getContract() {return this.contract;}
    public void setContract(Contract contract) {this.contract = contract;}

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="project_id")
    @JsonBackReference
    protected Project project;
    public Project getProject() {return this.project;}
    public void setProject(Project project) {this.project = project;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public ContributionID() {}

    public ContributionID(Contract contract, Project project)
    {
        this.setContract(contract);
        this.setProject(project);
    }
}
