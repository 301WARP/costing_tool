package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Embeddable;

import lombok.Data;


@Data
@Embeddable
public class EthicsChecklist
{
    private Boolean human;
    private Integer humanRef;

    private Boolean animal;
    private Integer animalRef;

    private Boolean drugs;

    private Boolean clinicalTrial;
}