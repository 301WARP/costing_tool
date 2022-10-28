package au.edu.utas.costing_tool.Model.Project;


import com.fasterxml.jackson.annotation.JsonBackReference;

// =============================================================================
// External Imports
// =============================================================================

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import au.edu.utas.costing_tool.Enums.Project.AMCMenzies;
import au.edu.utas.costing_tool.Enums.Project.AMCNationalCentre;
import au.edu.utas.costing_tool.Enums.Project.Category1Subtype;
import au.edu.utas.costing_tool.Enums.Project.CrowdFunding;
import au.edu.utas.costing_tool.Enums.Project.ProjectCategory;
import au.edu.utas.costing_tool.Enums.Project.ResearchEntity;
import au.edu.utas.costing_tool.Enums.Project.RhdInvolvement;
import au.edu.utas.costing_tool.Enums.Project.YearEndType;
import au.edu.utas.costing_tool.Model.Contribution.Contribution;
import au.edu.utas.costing_tool.Model.Entity.Unit;
import au.edu.utas.costing_tool.Model.Expense.Expense;
import au.edu.utas.costing_tool.Model.Researcher.ExternalResearcher;


// TODO(Andrew): Could convert codes to enums
//enum FieldCodes {}
//enum SEOCodes {}


@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name="project")
public class Project
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="herdc")
    private String herdc;

    @Column(name="funding_body")
    private String fundingBody;

    @Column(name="scheme")
    private String scheme;

    @Column(name="contact_name")
    private String contactName;

    @Column(name="contact_email")
    private String contactEmail;

    /*
    @OneToOne(cascade={ CascadeType.MERGE,
                        CascadeType.REFRESH,
                        CascadeType.PERSIST})
    @JoinColumn(name="lead_researcher_id", referencedColumnName="staff_id")
    private Researcher leadResearcher;
    */
    @Column(name="lead_researcher_name")
    private String leadResearcherName;

    @Column(name="lead_researcher_organisation")
    private String leadResearcherOrg;

    @Column(name="category")
    @Enumerated(value=EnumType.STRING)
    private ProjectCategory category;

    @Column(name="category_1_subtype")
    @Enumerated(value=EnumType.STRING)
    private Category1Subtype category1Subtype;

    @Column(name="amc_menzies")
    @Enumerated(value=EnumType.STRING)
    private AMCMenzies amcMenzies;

    @Column(name="amc_national_centre")
    @Enumerated(value=EnumType.STRING)
    private AMCNationalCentre amcNationalCentre;

    @Column(name="profit_margin")
    private Double profitMargin;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name="year_end_type")
    @Enumerated(value=EnumType.STRING)
    private YearEndType yearEnd;

    @Column(name="utas_cash")
    private Double utasCashContribution;

    @Column(name="partner_cash")
    private Double partnerCashContribution;

    @Column(name="entity")
    @Enumerated(value=EnumType.STRING)
    private ResearchEntity entity;

    @Column(name="crowd_funding_provider")
    @Enumerated(value=EnumType.STRING)
    private CrowdFunding crowdFundingProvider;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="code1", column=@Column(name="for_code_1")),
        @AttributeOverride(name="code2", column=@Column(name="for_code_2")),
        @AttributeOverride(name="code3", column=@Column(name="for_code_3")),
        @AttributeOverride(name="percent1", column=@Column(name="`for_%_1`")),
        @AttributeOverride(name="percent2", column=@Column(name="`for_%_2`")),
        @AttributeOverride(name="percent3", column=@Column(name="`for_%_3`"))
    })
    private FORCodes forCodes;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="code1", column=@Column(name="seo_code_1")),
        @AttributeOverride(name="code2", column=@Column(name="seo_code_2")),
        @AttributeOverride(name="code3", column=@Column(name="seo_code_3")),
        @AttributeOverride(name="percent1", column=@Column(name="`seo_%_1`")),
        @AttributeOverride(name="percent2", column=@Column(name="`seo_%_2`")),
        @AttributeOverride(name="percent3", column=@Column(name="`seo_%_3`"))
    })
    private SEOCodes seoCodes;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="appliedResearch", column=@Column(name="applied_research")),
        @AttributeOverride(name="experimentalDevelopment", column=@Column(name="experimental_development")),
        @AttributeOverride(name="strategicBasic", column=@Column(name="strategic_basic")),
        @AttributeOverride(name="pureBasic", column=@Column(name="pure_basic")),
    })
    private ResearchAreas researchAreas;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="human", column=@Column(name="human")),
        @AttributeOverride(name="humanRef", column=@Column(name="human_ref")),
        @AttributeOverride(name="animal", column=@Column(name="animal")),
        @AttributeOverride(name="animalRef", column=@Column(name="animal_ref")),
        @AttributeOverride(name="drugs", column=@Column(name="drugs")),
        @AttributeOverride(name="clinicalTrial", column=@Column(name="clinical_trial")),
    })
    EthicsChecklist ethicsChecklist;

    /*
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="ciEndorsement", column=@Column(name="ci_endorsement")),
        @AttributeOverride(name="ciEndorsementDate", column=@Column(name="ci_endorsement_date")),
        @AttributeOverride(name="riskAssessment", column=@Column(name="risk_assessment")),
        @AttributeOverride(name="utasInsurance", column=@Column(name="utas_insurance")),
        @AttributeOverride(name="defenceStrategicGoods", column=@Column(name="defence_strategic_goods")),
        @AttributeOverride(name="conflictOfInterest", column=@Column(name="conflict_of_interest")),
        @AttributeOverride(name="foreignPrincipals", column=@Column(name="foreign_principals")),
    })
    private CIEndorsement ciEndorsement;

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="project")
    @JsonManagedReference
    private List<DirectorEndorsement> directorEndorsements;

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="project")
    @JsonManagedReference
    private List<CollegeEndorsement> collegeEndorsements;
    */

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="project")
    @JsonManagedReference
    private List<Contribution> contributions;

    @OneToMany( cascade=CascadeType.MERGE,
                fetch=FetchType.LAZY,
                mappedBy="project")
    @JsonManagedReference
    private List<Expense> expenses;

    /*
    @Column(name="external_researchers")
    private String externalResearchers;
    public List<String> getExternalResearchers()
    {
        return new ArrayList<String>(
            Arrays.asList(
                this.externalResearchers
                    .split(",")
                )
        );
    }
    public void setExternalResearchers(List<String> researchers)
    {
        this.externalResearchers =
            researchers
                .stream()
                .collect(Collectors.joining(","));
    }


    @Column(name="external_organisations")
    private String externalOrgs;
    public List<String> getExternalOrgs()
    {
        return new ArrayList<String>(
            Arrays.asList(
                this.externalOrgs
                    .split(",")
                )
        );
    }
    public void setExternalOrgs(List<String> organisations)
    {
        this.externalResearchers =
            organisations
                .stream()
                .collect(Collectors.joining(","));
    }
    */
    

    @Column(name="external_researchers")
    private String externalResearchers;
    public List<ExternalResearcher> getExternalResearchers()
    {
        if (this.externalResearchers == null
            || this.externalResearchers.isEmpty())
            return null;

        List<String> researcherStrings =
            Arrays.asList(this.externalResearchers.split(";"));
        
        return researcherStrings
            .stream()
            .map(s -> s.split(","))
            .map(s -> ExternalResearcher
                .builder()
                .name(s.length > 0 ? s[0] : null)
                .organisation(s.length > 1 ? s[1] : null)
                .build())
            .collect(Collectors.toList());
    }
    public void setExternalResearchers(List<ExternalResearcher> researchers)
    {
        if (researchers == null || researchers.isEmpty()) {
            this.externalResearchers = null;
            return;
        }

        this.externalResearchers =
            researchers
                .stream()
                .map(r -> new StringJoiner(",")
                    .add(r.getName())
                    .add(r.getOrganisation())
                    .toString()
                )
                .collect(Collectors.joining(";"));
    }

    @Transient
    private List<ExternalResearcher> externalResearchersList;

    @Column(name="rhd_involvement")
    @Enumerated(value=EnumType.STRING)
    private RhdInvolvement rhdInvolvement;


    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rhd_unit_id", referencedColumnName="id")
    @JsonBackReference
    @ToString.Exclude
    protected Unit rhdUnit;
    

    @Column(name="utas_dvcr_cash")
    private Double dvcrCash;


    // =========================================================================
    // Constructors
    // =========================================================================

    public Project()
    {
        this.setContributions(new ArrayList<Contribution>());
        this.setExpenses(new ArrayList<Expense>());
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

    public boolean addExpense(Expense expense)
    {
        return this.getExpenses().add(expense);
    }

    public boolean removeExpense(Expense expense)
    {
        return this.getExpenses().remove(expense);
    }
}