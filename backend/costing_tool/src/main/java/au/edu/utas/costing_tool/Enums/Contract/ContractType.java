package au.edu.utas.costing_tool.Enums.Contract;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ContractType {
    NONE("None"),

    NON_CASUAL("Non-Casual"),
    CASUAL("Casual"),
    RHD("RHD"),

    ;
    String name;
}