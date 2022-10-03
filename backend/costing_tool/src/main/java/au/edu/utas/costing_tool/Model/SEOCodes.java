package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class SEOCodes
{
    private Integer code1;
    private Double percent1;

    private Integer code2;
    private Double percent2;

    private Integer code3;
    private Double percent3;
}