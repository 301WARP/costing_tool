package au.edu.utas.costing_tool.Enums.Expense;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum AuditType {
    NONE("None"),
    INTERNAL("Internal"),


    ;
    private final String name;


    public static AuditType ofName(String name)
    {
        switch (name)
        {
            case "none":
                return NONE;
            case "Internal":
                return INTERNAL;
        }

        return null;
    }
}
