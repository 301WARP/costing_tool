package au.edu.utas.costing_tool.Enums.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum AMCMenzies {
    NONE("None"),

    AMC("AMC"),
    MENZIES("Menzies"),
    ;

    String name;
}