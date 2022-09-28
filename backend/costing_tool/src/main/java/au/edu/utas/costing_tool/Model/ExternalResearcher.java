package au.edu.utas.costing_tool.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExternalResearcher
{
    private String name;
    private String organisation;
}