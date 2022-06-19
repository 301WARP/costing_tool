package au.edu.utas.costing_tool.Model;

// Inbuilt imports
import java.util.Dictionary;
import org.springframework.data.util.Pair;

enum Ethics {}
enum Endorsement {}

public class ClearanceForm
{
    public Dictionary<Ethics, Pair<Boolean, Integer>> Ethics;
    public Dictionary<Endorsement, Boolean> CheckList;
    public boolean CIEndorsement;
    public boolean DirectorEndorsement;
    public Boolean CollegeEndorsement;
}
