package au.edu.utas.costing_tool.Model;

// Inbuilt imports
import java.time.LocalDate;
import java.util.Dictionary;

public class Cost
{
    public CostType Type;
    public double CostPerUnit;
    public Dictionary<LocalDate, Double> UnitsPerYear;
    public double InKindPercent;
    public double InKindDouble;
}
