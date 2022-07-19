package au.edu.utas.costing_tool.Model;


// =============================================================================
// External imports
// =============================================================================

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
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

import com.fasterxml.jackson.annotation.JsonManagedReference;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.Title;


@Entity
@Table(name="researcher")
public class Researcher
{
    // =========================================================================
    // Properties
    // =========================================================================

    // TODO(Andrew): I'm not sure if staff ids should be generated
    @Id
    @GeneratedValue
    @Column(name="staff_id")
    @Access(AccessType.FIELD)
    public Long staffID;
    public Long getStaffID() {return this.staffID;}
    public void setStaffID(Long id) {this.staffID = id;}

    @Column(name="title")
    @Enumerated(EnumType.STRING)
    public Title title;
    public Title getTitle() {return this.title;}
    public void setTitle(Title title) {this.title = title;}

    @Column(name="first_name")
    public String firstName;
    public String getFirstName() {return this.firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    @Column(name="last_name")
    public String lastName;
    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    @OneToMany( cascade=CascadeType.ALL,
                fetch=FetchType.LAZY,
                mappedBy="researcher")
    @JsonManagedReference
    public List<Contract> contracts;
    public List<Contract> getContracts() {return this.contracts;}
    public void setContracts(List<Contract> contracts) {this.contracts = contracts;}


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

    public boolean addContract(Contract contract)
    {
        return this.getContracts().add(contract);
    }

    public boolean removeContract(Contract contract)
    {
        return this.getContracts().remove(contract);
    }
}