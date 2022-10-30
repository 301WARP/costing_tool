package au.edu.utas.costing_tool.Model.Contribution;


// =============================================================================
// External imports
// =============================================================================

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContributionID implements Serializable
{
    private static final long serialVersionUID = 1L;

    protected Long contractID;
    protected Long projectID;
}