package au.edu.utas.costing_tool.Model.Expense;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// =============================================================================
// Project Imports
// =============================================================================

//import au.edu.utas.costing_tool.Enums.Expense.AuditType;
import au.edu.utas.costing_tool.Enums.Expense.Units;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="audit_fee")
public class AuditFee
{
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="type")
    //@Enumerated(value=EnumType.STRING)
    //private AuditType type;
    private String type;
    
    @Column(name="cost_per_unit")
    private Double costPerUnit;

    @Column(name="units")
    @Enumerated(EnumType.STRING)
    private Units units; 

    @Column(name="notes")
    private String notes;
}
