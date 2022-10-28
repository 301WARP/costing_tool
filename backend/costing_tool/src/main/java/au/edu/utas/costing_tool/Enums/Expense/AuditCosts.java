package au.edu.utas.costing_tool.Enums.Expense;


import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum AuditCosts {
    NONE(
        "None",
        0.00,
        Units.NONE
    ),

    EXTERNAL(
        "External",
        2_200.00,
        Units.AUDITS
    ),

    ;
    String name;
    Double costPerUnit;
    Units units;
}
