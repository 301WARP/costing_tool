package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
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
import lombok.ToString;


@Data
@Entity
@Table(name="college_endorsement")
@IdClass(value=CollegeEndorsementID.class)
public class CollegeEndorsement
{
    @Id
    @Column(name="project_id")
    private Long projectId;

    @Id
    @Column(name="college_id")
    private Long collegeId;

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
    private LocalDate date;

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
    private College college;
}