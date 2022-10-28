package au.edu.utas.costing_tool.Enums.Project;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CrowdFunding {
    NONE("None"),

    POZIBLE("Pozible"),
    ;

    String name;
}