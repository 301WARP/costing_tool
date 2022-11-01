package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.Contract.ContractDTO;

import au.edu.utas.costing_tool.Model.Contract.Casual;
import au.edu.utas.costing_tool.Model.Contract.Contract;
import au.edu.utas.costing_tool.Model.Contract.NonCasual;
import au.edu.utas.costing_tool.Model.Contract.RHD;


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
    @Mapping(source="staffType", target="staffType")
    @Mapping(source="classification", target="classification")
    @Mapping(source="step", target="step", qualifiedByName="enumToInteger")
    @Mapping(source="paymentDetails.salary", target="salary")
    @BeanMapping(ignoreByDefault=true)
    public ContractDTO nonCasualMap(NonCasual contract);

    @Mapping(source="id", target="id")
    @Mapping(source="contractType", target="type")
    @Mapping(source="staffType", target="staffType")
    @Mapping(source="classification", target="classification")
    @Mapping(source="payCode", target="payCode", qualifiedByName="enumToInteger")
    @Mapping(source="paymentDetails.hourlyRate", target="hourlyRate")
    @BeanMapping(ignoreByDefault=true)
    public ContractDTO casualMap(Casual contract);

    @Mapping(source="id", target="id")
    @Mapping(source="contractType", target="type")
    @Mapping(source="classification", target="classification")
    @Mapping(source="wageExpense", target="salary")
    @BeanMapping(ignoreByDefault=true)
    public ContractDTO rhdMap(RHD contract);

    @Named("enumToString")
    static String enumToString(Enum<?> e)
    {
        return e.toString();
    }

    @Named("enumToInteger")
    static Integer enumToInteger(Enum<?> e)
    {
        return e.ordinal();
    }
}
