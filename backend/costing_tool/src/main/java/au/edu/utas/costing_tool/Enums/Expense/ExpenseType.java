package au.edu.utas.costing_tool.Enums.Expense;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpenseType {
    NONE("None"),

    TRAVEL("Travel"),
    LABORATORY_HIRE("Facilities/Laboratory hire"),
    CONSUMABLES("Consumables"),
    EQUIPMENT_PURCHASES("Equipment purchases"),
    EXTERNAL_CONTRACTOR("External contractor"),
    OTHER_COSTS("Other costs"),
    AUDIT_FEES("Audit fees"),
    RHD_NON_STIPEND_COSTS("RHD non-stipend costs"),
    FACILITY_COSTS("Facitlity costs"),
    PARTNER_ORGANISATION("Partner Organisation"),


    ;
    public final String name;
}