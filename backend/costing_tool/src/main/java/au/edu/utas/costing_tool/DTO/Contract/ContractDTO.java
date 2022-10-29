package au.edu.utas.costing_tool.DTO.Contract;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContractDTO
{
    Long id;
    String type;

    String staffType;
    String classification;
    Integer payCode;
    Integer step;

    Double salary;
    Double hourlyRate;
}
