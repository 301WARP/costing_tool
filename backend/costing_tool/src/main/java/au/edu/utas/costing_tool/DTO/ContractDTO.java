package au.edu.utas.costing_tool.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContractDTO
{
    Long id;
    String type;
}
