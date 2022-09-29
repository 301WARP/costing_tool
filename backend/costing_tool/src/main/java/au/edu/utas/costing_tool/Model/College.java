package au.edu.utas.costing_tool.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="college")
public class College
{
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;
    
    @OneToOne(cascade={ CascadeType.MERGE,
                        CascadeType.REFRESH,
                        CascadeType.PERSIST})
    @JoinColumn(name="executive_dean_id", referencedColumnName="staff_id")
    protected Researcher executiveDean;
}
