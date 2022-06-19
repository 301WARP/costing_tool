package au.edu.utas.costing_tool.Model;

// Inbuilt imports
import java.util.Dictionary;
import java.time.LocalDate;

enum RHDIncomeStream {}

public class RHD extends Contract
{
    public RHDIncomeStream Classification;
    public double AnnualSalary;
    public Dictionary<LocalDate, Double> FTE;
}
