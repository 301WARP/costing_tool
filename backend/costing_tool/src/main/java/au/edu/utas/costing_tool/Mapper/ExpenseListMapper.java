package au.edu.utas.costing_tool.Mapper;


// =============================================================================
// External Imports
// =============================================================================

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.Expense.ExpenseListDTO;
import au.edu.utas.costing_tool.Model.Expense.Expense;


@Mapper
public interface ExpenseListMapper
{
    default List<ExpenseListDTO> map(List<Expense> expenses)
    {
        return expenses
            .stream()
            .map(x -> this.map(x))
            .collect(Collectors.toList());
    }

    @Mapping(target="id", source="id")
    @Mapping(target="type", source="type")
    @Mapping(target="description", source="description")
    @Mapping(target="inKindPercent", source="inKindPercent")
    @Mapping(   target="actualCost", source="expense",
                qualifiedBy=ActualCostMap.class)
    public
    ExpenseListDTO map(Expense expense);

    @ActualCostMap
    public static Double costToActualCost(Expense expense)
    {
        if (expense == null)
            return 0.0;

        return expense.Cost();
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ActualCostMap {}
}