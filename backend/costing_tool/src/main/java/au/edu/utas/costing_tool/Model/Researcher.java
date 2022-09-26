package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;
//import java.util.StringJoiner;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.Title;


@Data
@Entity
@Table(name="researcher")
public class Researcher
{
    // =========================================================================
    // Properties
    // =========================================================================

    //public static final Long EXTERNAL_ID = 1L;

    // TODO(Andrew): I'm not sure if staff ids should be generated
    @Id
    @GeneratedValue
    @Column(name="staff_id")
    public Long staffID;

    @Column(name="title")
    @Enumerated(EnumType.STRING)
    public Title title;

    @Column(name="first_name")
    public String firstName;

    @Column(name="last_name")
    public String lastName;

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="researcher")
    @JsonManagedReference
    public List<Contract> contracts;


    // =========================================================================
    // Constructors
    // =========================================================================

    public Researcher()
    {
        this.setContracts(new ArrayList<Contract>());
    }

    public Researcher(Title title, String firstName, String lastName)
    {
        this();
        this.setTitle(title);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public Researcher(Long id, Title title, String firstName, String lastName)
    {
        this();
        this.setStaffID(id);
        this.setTitle(title);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }


    // =========================================================================
    // Methods
    // =========================================================================

    /*
    public String getName()
    {
        if (this.staffID.equals(Researcher.EXTERNAL_ID))
            return this.getExternal().getName();
        
        return new StringJoiner(" ")
            .add(this.getTitle().toString())
            .add(this.getFirstName())
            .add(this.getLastName())
            .toString();
    }
    */

    public boolean addContract(Contract contract)
    {
        return this.getContracts().add(contract);
    }

    public boolean removeContract(Contract contract)
    {
        return this.getContracts().remove(contract);
    }
}