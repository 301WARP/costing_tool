package au.edu.utas.costing_tool.Model;

// Inbuilt imports
import java.util.Dictionary;
import java.time.LocalDate;

enum NonCasualClassification {}
enum Step {}

public class NonCasual extends Staff
{
    public NonCasualClassification Classification;
    public Step Step;
    public double StartingSalary;
    public Dictionary<LocalDate, Double> FTE;
}
