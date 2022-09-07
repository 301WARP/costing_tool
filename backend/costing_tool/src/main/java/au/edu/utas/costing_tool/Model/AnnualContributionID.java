package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnualContributionID implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long contractID;
    private Long projectID;
    private Integer year;
}