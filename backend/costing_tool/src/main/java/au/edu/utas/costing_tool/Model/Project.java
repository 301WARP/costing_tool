package au.edu.utas.costing_tool.Model;

// Inbuilt imports
import java.util.List;
import java.time.LocalDate;

enum ProjectCategory {}
enum AMC_Menzies {}
enum YearEnd {}
enum Entity {}
enum CrowdFunding {}
enum FieldCodes {}
enum SEOCodes {}

public class Project
{
    public String Name;
    public Researcher LeadResearcher;
    public ProjectCategory Category;
    public AMC_Menzies AMC_Menzies;
    public LocalDate StartDate;
    public LocalDate EndDate;
    public YearEnd YearEndType;
    public Double UTASCashContribution;
    public Double PartnerCashContribution;
    public CrowdFunding CrowdFundingProvider;
    public List<SEOCodes> ResearchFieldCodes;

    public List<Contract> Contracts;
    public List<Cost> Costs;
    public ClearanceForm ClearanceForm;
}
