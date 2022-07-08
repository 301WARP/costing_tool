package au.edu.utas.costing_tool.Model;


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
    private Long id;
    public Long getID() {return this.id;}
    public void setID(Long id) {this.id = id;}

    @Column(name="name")
    private String name;
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="lead_researcher_id", referencedColumnName="id")
    private Researcher leadResearcher;
    public Researcher getLeadResearcher() {return this.leadResearcher;}
    public void setLeadResearcher(Researcher leadResearcher)
        {this.leadResearcher = leadResearcher;}

    @Column(name="category")
    private ProjectCategory category;
    public ProjectCategory getCategory() {return this.category;}
    public void setCategory(ProjectCategory category)
        {this.category = category;}

    @Column(name="amc_menzies")
    private AMCMenzies amcMenzies;
    public AMCMenzies getAmcMenzies() {return this.amcMenzies;}
    public void setAmcMenzies(AMCMenzies amcMenzies)
        {this.amcMenzies = amcMenzies;}

    @Column(name="start_date")
    private LocalDate startDate;
    public LocalDate getStartDate() {return this.startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    @Column(name="end_date")
    private LocalDate endDate;
    public LocalDate getEndDate() {return this.endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}

    @Column(name="year_end")
    private YearEndType yearEnd;
    public YearEndType getYearEnd() {return this.yearEnd;}
    public void setYearEnd(YearEndType yearEnd) {this.yearEnd = yearEnd;}

    @Column(name="utas_cash")
    private Double utasCashContribution;
    public Double getUTASCashContribution() {return this.utasCashContribution;}
    public void setUTASCashContribution(Double cash)
        {this.utasCashContribution = cash;}

    @Column(name="partner_cash")
    private Double partnerCashContribution;
    public Double getPartnerCashContribution() {return this.partnerCashContribution;}
    public void setPartnerCashContribution(Double cash)
        {this.partnerCashContribution = cash;}

    @Column(name="crowd_funding_provider")
    private CrowdFunding crowdFundingProvider;
    public CrowdFunding getCrowdFundingProvider()
        {return this.crowdFundingProvider;}
    public void setCrowdFundingProvider(CrowdFunding provider)
        {this.crowdFundingProvider = provider;}

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="code1", column=@Column(name="for_code_1")),
        @AttributeOverride(name="code2", column=@Column(name="for_code_2")),
        @AttributeOverride(name="code3", column=@Column(name="for_code_3")),
        @AttributeOverride(name="percent1", column=@Column(name="for_%_1")),
        @AttributeOverride(name="percent2", column=@Column(name="for_%_2")),
        @AttributeOverride(name="percent3", column=@Column(name="for_%_3"))
    })
    private FORCodes forCodes;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="code1", column=@Column(name="soe_code_1")),
        @AttributeOverride(name="code2", column=@Column(name="soe_code_2")),
        @AttributeOverride(name="code3", column=@Column(name="soe_code_3")),
        @AttributeOverride(name="percent1", column=@Column(name="soe_%_1")),
        @AttributeOverride(name="percent2", column=@Column(name="soe_%_2")),
        @AttributeOverride(name="percent3", column=@Column(name="soe_%_3"))
    })
    private SOECodes seoCodes;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="code1", column=@Column(name="soe_code_1")),
        @AttributeOverride(name="code2", column=@Column(name="soe_code_2")),
        @AttributeOverride(name="code3", column=@Column(name="soe_code_3")),
        @AttributeOverride(name="percent1", column=@Column(name="soe_%_1")),
        @AttributeOverride(name="percent2", column=@Column(name="soe_%_2")),
        @AttributeOverride(name="percent3", column=@Column(name="soe_%_3"))
    })
    private ResearchAreas researchAreas;

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

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="ciEndorsement", column=@Column(name="ci_endorsement")),
        @AttributeOverride(name="ciEndorsementDate", column=@Column(name="ci_endorsement_date")),
        @AttributeOverride(name="riskAssessment", column=@Column(name="risk_assessment")),
        @AttributeOverride(name="risksManaged", column=@Column(name="risks_managed")),
        @AttributeOverride(name="utasInsurance", column=@Column(name="utas_insurance")),
        @AttributeOverride(name="defenceStrategicGoods", column=@Column(name="defences_trategic_goods")),
        @AttributeOverride(name="conflictOfInterst", column=@Column(name="conflict_of_interest")),
        @AttributeOverride(name="foreignPrincipals", column=@Column(name="foreign_principals")),
    })
    CIEndorsement ciEndorsement;

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
    DirectorEndorsement directorEndorsement;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="collegeEndorsement", column=@Column(name="college_endorsement")),
        @AttributeOverride(name="collegeEndorsementDate", column=@Column(name="college_endorsement_date")),
    })
    CollegeEndorsement collegeEndorsement;


    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="id.project")
    @JsonManagedReference
    public List<Contribution> contributions;


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

    Project() {}


    // =========================================================================
    // Methods
    // =========================================================================

    public boolean addExpense(Expense expense)
    {
        return this.getExpenses().add(expense);
    }

    public boolean removeExpense(Expense expense)
    {
        return this.getExpenses().remove(expense);
    }
}