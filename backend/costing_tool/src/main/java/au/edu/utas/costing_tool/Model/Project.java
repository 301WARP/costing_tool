package au.edu.utas.costing_tool.Model;


import java.util.ArrayList;

// =============================================================================
// External Imports
// =============================================================================

import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.AMCMenzies;
import au.edu.utas.costing_tool.Enums.CrowdFunding;
import au.edu.utas.costing_tool.Enums.ProjectCategory;
import au.edu.utas.costing_tool.Enums.ResearchEntity;
import au.edu.utas.costing_tool.Enums.YearEndType;


// TODO(Andrew): Could convert codes to enums
//enum FieldCodes {}
//enum SEOCodes {}


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
    public Long getID() {return this.id;}
    public void setID(Long id) {this.id = id;}

    @Column(name="name")
    private String name;
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    @Column(name="description")
    private String description;
    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="lead_researcher_id", referencedColumnName="staff_id")
    private Researcher leadResearcher;
    public Researcher getLeadResearcher() {return this.leadResearcher;}
    public void setLeadResearcher(Researcher leadResearcher) {this.leadResearcher = leadResearcher;}

    @Column(name="category")
    @Enumerated(value=EnumType.STRING)
    private ProjectCategory category;
    public ProjectCategory getCategory() {return this.category;}
    public void setCategory(ProjectCategory category) {this.category = category;}

    @Column(name="amc_menzies")
    @Enumerated(value=EnumType.STRING)
    private AMCMenzies amcMenzies;
    public AMCMenzies getAmcMenzies() {return this.amcMenzies;}
    public void setAmcMenzies(AMCMenzies amcMenzies) {this.amcMenzies = amcMenzies;}

    @Column(name="start_date")
    private LocalDate startDate;
    public LocalDate getStartDate() {return this.startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    @Column(name="end_date")
    private LocalDate endDate;
    public LocalDate getEndDate() {return this.endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}

    @Column(name="year_end_type")
    @Enumerated(value=EnumType.STRING)
    private YearEndType yearEnd;
    public YearEndType getYearEnd() {return this.yearEnd;}
    public void setYearEnd(YearEndType yearEnd) {this.yearEnd = yearEnd;}

    @Column(name="utas_cash")
    private Double utasCashContribution;
    public Double getUTASCashContribution() {return this.utasCashContribution;}
    public void setUTASCashContribution(Double cash) {this.utasCashContribution = cash;}

    @Column(name="partner_cash")
    private Double partnerCashContribution;
    public Double getPartnerCashContribution() {return this.partnerCashContribution;}
    public void setPartnerCashContribution(Double cash) {this.partnerCashContribution = cash;}

    @Column(name="entity")
    @Enumerated(value=EnumType.STRING)
    private ResearchEntity entity;
    public ResearchEntity getEntity() {return this.entity;}
    public void setEntity(ResearchEntity entity) {this.entity = entity;}

    @Column(name="crowd_funding_provider")
    @Enumerated(value=EnumType.STRING)
    private CrowdFunding crowdFundingProvider;
    public CrowdFunding getCrowdFundingProvider() {return this.crowdFundingProvider;}
    public void setCrowdFundingProvider(CrowdFunding provider) {this.crowdFundingProvider = provider;}

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
    public FORCodes getForCodes() {return this.forCodes;}
    public void getForCodes(FORCodes codes) {this.forCodes = codes;}

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
    public SEOCodes getSeoCodes() {return this.seoCodes;}
    public void getSeoCodes(SEOCodes codes) {this.seoCodes = codes;}

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="appliedResearch", column=@Column(name="applied_research")),
        @AttributeOverride(name="experimentalDevelopment", column=@Column(name="experimental_development")),
        @AttributeOverride(name="strategicBasic", column=@Column(name="strategic_basic")),
        @AttributeOverride(name="pureBasic", column=@Column(name="pure_basic")),
    })
    private ResearchAreas researchAreas;
    public ResearchAreas getResearcherAreas() {return this.researchAreas;}
    public void getResearcherAreas(ResearchAreas areas) {this.researchAreas = areas;}

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="humanMedical", column=@Column(name="human_medical")),
        @AttributeOverride(name="humanMedicalRef", column=@Column(name="human_medical_ref")),
        @AttributeOverride(name="humanSocialScience", column=@Column(name="human_social_science")),
        @AttributeOverride(name="humanSocialScienceRef", column=@Column(name="human_social_science_ref")),
        @AttributeOverride(name="animals", column=@Column(name="animals")),
        @AttributeOverride(name="animalsRef", column=@Column(name="animals_ref")),
        @AttributeOverride(name="gmo", column=@Column(name="gmo")),
        @AttributeOverride(name="gmoRef", column=@Column(name="gmo_ref")),
        @AttributeOverride(name="raidation", column=@Column(name="raidation")),
        @AttributeOverride(name="raidationRef", column=@Column(name="raidation_ref")),
        @AttributeOverride(name="carcinogenTeratogen", column=@Column(name="carcinogen_teratogen")),
        @AttributeOverride(name="carcinogenTeratogenRef", column=@Column(name="carcinogen_teratogen_ref")),
    })
    EthicsChecklist ethicsChecklist;
    public EthicsChecklist getEthicsChecklist() {return this.ethicsChecklist;}
    public void setEthicsChecklist(EthicsChecklist ethics) {this.ethicsChecklist = ethics;}

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="ciEndorsement", column=@Column(name="ci_endorsement")),
        @AttributeOverride(name="ciEndorsementDate", column=@Column(name="ci_endorsement_date")),
        @AttributeOverride(name="riskAssessment", column=@Column(name="risk_assessment")),
        @AttributeOverride(name="risksManaged", column=@Column(name="risks_managed")),
        @AttributeOverride(name="utasInsurance", column=@Column(name="utas_insurance")),
        @AttributeOverride(name="defenceStrategicGoods", column=@Column(name="defence_strategic_goods")),
        @AttributeOverride(name="conflictOfInterst", column=@Column(name="conflict_of_interest")),
        @AttributeOverride(name="foreignPrincipals", column=@Column(name="foreign_principals")),
    })
    private CIEndorsement ciEndorsement;
    public CIEndorsement getCIEndorsement() {return this.ciEndorsement;}
    public void getCIEndorsement(CIEndorsement endorsement) {this.ciEndorsement = endorsement;}

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="org1", column=@Column(name="organisational_unit_1")),
        @AttributeOverride(name="org1Split", column=@Column(name="organisational_unit_1_split")),
        @AttributeOverride(name="directorEndorsement1", column=@Column(name="director_endorsement_1")),
        @AttributeOverride(name="directorEndorsement1Date", column=@Column(name="director_endorsement_1_date")),
        @AttributeOverride(name="org2", column=@Column(name="organisational_unit_2")),
        @AttributeOverride(name="org2Split", column=@Column(name="organisational_unit_2_split")),
        @AttributeOverride(name="directorEndorsement2", column=@Column(name="director_endorsement_2")),
        @AttributeOverride(name="directorEndorsement2Date", column=@Column(name="director_endorsement_2_date")),
    })
    private DirectorEndorsement directorEndorsement;
    public DirectorEndorsement getDirectorEndorsement() {return this.directorEndorsement;}
    public void getDirectorEndorsement(DirectorEndorsement endorsement) {this.directorEndorsement = endorsement;}

    // FIXME(Andrew): only displays endorsement
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="endorsement", column=@Column(name="college_endorsement")),
        @AttributeOverride(name="date", column=@Column(name="college_endorsement_date"))
    })
    private CollegeEndorsement collegeEndorsement;
    public CollegeEndorsement getCollegeEndorsement() {return this.collegeEndorsement;}
    public void getCollegeEndorsement(CollegeEndorsement endorsement) {this.collegeEndorsement = endorsement;}

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="project")
    @JsonManagedReference
    private List<Contribution> contributions;
    public List<Contribution> getContributions() {return this.contributions;}
    public void setContributions(List<Contribution> contributions) {this.contributions = contributions;}

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="project")
    @JsonManagedReference
    private List<Expense> expenses;
    public List<Expense> getExpenses() {return this.expenses;}
    public void setExpenses(List<Expense> expenses) {this.expenses = expenses;}



    // =========================================================================
    // Constructors
    // =========================================================================

    public Project()
    {
        this.setContributions(new ArrayList<Contribution>());
        this.setExpenses(new ArrayList<Expense>());
    }

    public Project( Long id,
                    String name,
                    String description,
                    Researcher leadResearcher,
                    AMCMenzies amcMenzies,
                    LocalDate startDate,
                    LocalDate endDate,
                    YearEndType yearEnd,
                    Double utasCashContribution,
                    Double partnerCashContribution,
                    ResearchEntity entity,
                    CrowdFunding crowdFundingProvider)
    {
        this();
        this.setID(id);
        this.setName(name);
        this.setDescription(description);
        this.setLeadResearcher(leadResearcher);
        this.setAmcMenzies(amcMenzies);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setYearEnd(yearEnd);
        this.setUTASCashContribution(utasCashContribution);
        this.setPartnerCashContribution(partnerCashContribution);
        this.setEntity(entity);
        this.setCrowdFundingProvider(crowdFundingProvider);
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