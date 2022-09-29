package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


// TODO(Andrew): Consider creating new table for DirectorEndorsement
@Data
@NoArgsConstructor
@Entity
@Table(name="director_endorsement")
@IdClass(value=DirectorEndorsementID.class)
public class DirectorEndorsement
{
    @Id
    @Column(name="project_id")
    private Long projectId;

    @Id
    @Column(name="unit_id")
    private Long unitId;

    @Column(name="project_costs")
    private Boolean projectCosts;

    @Column(name="facilities")
    private Boolean facilities;

    @Column(name="blessing")
    private Boolean blessing;

    @Column(name="workload")
    private Boolean workload;

    @Column(name="ethics")
    private Boolean ethics;

    @Column(name="conflict_of_interest")
    private Boolean conflictOfInterest;

    @Column(name="split")
    private Double split;

    @OneToOne(cascade={ CascadeType.MERGE,
                        CascadeType.REFRESH,
                        CascadeType.PERSIST})
    @JoinColumn(name="endorser_id", referencedColumnName="staff_id")
    private Researcher endorser;

    @Column(name="endorsement")
    private Boolean endorsed;

    @Column(name="endorsement_date")
    private LocalDate endorsementDate;

    @ManyToOne
    @MapsId
    @JoinColumn(name="project_id", referencedColumnName="id")
    @ToString.Exclude
    @JsonBackReference
    private Project project;

    @ManyToOne
    @MapsId
    @JoinColumn(name="project_id", referencedColumnName="id")
    @ToString.Exclude
    @JsonBackReference
    private Unit unit;
}