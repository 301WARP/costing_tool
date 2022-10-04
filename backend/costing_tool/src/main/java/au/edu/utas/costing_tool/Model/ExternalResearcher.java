package au.edu.utas.costing_tool.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExternalResearcher
{
    private String name;
    private String organisation;
}