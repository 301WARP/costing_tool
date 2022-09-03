package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContributionID implements Serializable
{
    private static final long serialVersionUID = 1L;

    protected Long contractID;
    protected Long projectID;
}