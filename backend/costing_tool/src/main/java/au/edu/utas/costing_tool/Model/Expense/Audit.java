package au.edu.utas.costing_tool.Model.Expense;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@Entity
@DiscriminatorValue("AUDIT_FEES")
public class Audit extends Expense
{
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="audit_fee_id", referencedColumnName="id")
    private AuditFee auditFee;

    @Override
    public Double getCostPerUnit() {
        return this.auditFee.getCostPerUnit();
    }
}