package au.edu.utas.costing_tool.Enums.Project;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Category1Subtype {
    NONE("None"),

    ARC("ARC"),
    NHMRC("NHMRC/DECRA/DORA/DIA"),
    OTHER("Other"),
    ;

    String name;
}
