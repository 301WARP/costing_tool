package au.edu.utas.costing_tool.Enums.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AMCNationalCentre {
    NONE("Name"),

    NCMEH("NCMEH"),
    NCPS("NCPS"),
    ;

    String name;
}