package au.edu.utas.costing_tool.Enums.Expense;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Facility
{
    NONE("None"),

    ANIMAL("Animal"),
    AMC("AMC"),
    CSL("CSL"),
    IMAS("IMAS"),
    MENZIES("Menzies"),
    TIA("TIA"),

    ;
    String name;
}