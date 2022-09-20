package au.edu.utas.costing_tool.Model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CollegeEndorsementID implements Serializable
{
    private static final long serialVersionUID = 1L;

    protected Long projectId;
    protected Long collegeId;
}
