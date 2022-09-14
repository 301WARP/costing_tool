package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Embeddable;

import lombok.Data;


// TODO(Andrew): Consider making this a separate table
@Data
@Embeddable
public class FORCodes
{
    private Integer code1;
    private Double percent1;

    private Integer code2;
    private Double percent2;

    private Integer code3;
    private Double percent3;
}