package au.edu.utas.costing_tool.Enums.Contract;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CasualStaffType {
    NONE("Name"),

    ACADEMIC("Academic"),
    PROFESSIONAL("Professional"),
    TEACHING("Non Award Teaching"),
    CONSERVATORIUM("Conservatorium/AMEB"),
    ;

    String name;
}