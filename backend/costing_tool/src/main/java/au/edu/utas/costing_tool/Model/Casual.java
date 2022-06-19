package au.edu.utas.costing_tool.Model;

// Inbuilt imports
import java.time.LocalDate;
import java.util.Dictionary;

enum CasualClassification {}
enum PayCode {}

public class Casual extends Staff
{
    public CasualClassification Classification;
    public PayCode PayCode;
    public double HourlyRate;
    public Dictionary<LocalDate, Double> Hours;
}
