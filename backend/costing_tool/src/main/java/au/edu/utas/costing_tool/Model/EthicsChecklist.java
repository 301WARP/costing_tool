package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Embeddable
public class EthicsChecklist
{
    private Boolean human;
    private String humanRef;

    private Boolean animal;
    private String animalRef;

    private Boolean drugs;

    private Boolean clinicalTrial;
}