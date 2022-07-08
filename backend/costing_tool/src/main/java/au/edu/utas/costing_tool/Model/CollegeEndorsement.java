package au.edu.utas.costing_tool.Model;

import java.time.LocalDate;

import javax.persistence.Embeddable;

@Embeddable
public class CollegeEndorsement
{
    private Boolean collegeEndorsement;
    public Boolean getCollegeEndorsement() {return this.collegeEndorsement;}
    public void setCollegeEndorsement(Boolean endorsed) {this.collegeEndorsement = endorsed;}

    private LocalDate collegeEndorsementDate;
    public LocalDate getCollegeEndorsementDate() {return this.collegeEndorsementDate;}
    public void setCollegeEndorsementDate(LocalDate date) {this.collegeEndorsementDate = date;}
    
}