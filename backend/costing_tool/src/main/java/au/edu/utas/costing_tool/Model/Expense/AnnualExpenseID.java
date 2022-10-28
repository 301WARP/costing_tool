package au.edu.utas.costing_tool.Model.Expense;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnualExpenseID implements Serializable
{
    protected Long expenseID;
    protected Integer year;
}