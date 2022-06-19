package au.edu.utas.costing_tool.Model;

// Inbuilt imports
import java.time.LocalDate;

public class Travel extends Cost
{
    public int NumTravelers;
    public LocalDate Departure;
    public LocalDate Return;
    public double Fare;
    public double Accommodation;
    public double CarHire;
}
