package au.edu.utas.costing_tool.Model.Project;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorEndorsementID implements Serializable
{
    private static final long serialVersionUID = 1L;

    protected Long projectId;
    protected Long unitId;
}
