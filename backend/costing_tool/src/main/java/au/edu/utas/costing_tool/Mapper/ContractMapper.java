package au.edu.utas.costing_tool.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;

import au.edu.utas.costing_tool.DTO.ContractDTO;
import au.edu.utas.costing_tool.Model.Casual;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Model.NonCasual;
import au.edu.utas.costing_tool.Model.RHD;

@Mapper
public interface ContractMapper
{
    default List<ContractDTO> map(List<Contract> contracts)
    {
        return contracts
            .stream()
            .map(c -> this.map(c))
            .collect(Collectors.toList());
    }

    @SubclassMapping(source=NonCasual.class, target=ContractDTO.class)
    @SubclassMapping(source=Casual.class, target=ContractDTO.class)
    @SubclassMapping(source=RHD.class, target=ContractDTO.class)
    @BeanMapping(ignoreByDefault=true)
    public ContractDTO map(Contract contract);

    @Mapping(source="id", target="id")
    @Mapping(source="contractType", target="type")
    public ContractDTO nonCasualMap(NonCasual contract);

    @Mapping(source="id", target="id")
    @Mapping(source="contractType", target="type")
    public ContractDTO casualMap(Casual contract);

    @Mapping(source="id", target="id")
    @Mapping(source="contractType", target="type")
    public ContractDTO rhdMap(RHD contract);
}