package au.edu.utas.costing_tool.Model;

import java.time.LocalDate;

import javax.persistence.Embeddable;

// TODO(Andrew): Remove 1/2 and have two copies of class in Project?
@Embeddable
public class DirectorEndorsement
{
    private String org1;
    public String getOrg1() {return this.org1;}
    public void setOrg1(String unit) {this.org1 = unit;}

    private Double org1Split;
    public Double getOrg1Split() {return this.org1Split;}
    public void setOrg1Split(Double percent) {this.org1Split = percent;}

    private Boolean directorEndorsement1;
    public Boolean getDirectorEndorsement1() {return this.directorEndorsement1;}
    public void setDirectorEndorsement1(Boolean endorsed) {this.directorEndorsement1 = endorsed;}

    private LocalDate directorEndorsement1Date;
    public LocalDate getDirectorEndorsement1Date() {return this.directorEndorsement1Date;}
    public void setDirectorEndorsement1Date(LocalDate date) {this.directorEndorsement1Date = date;}

    private String org2;
    public String getOrg2() {return this.org2;}
    public void setOrg2(String unit) {this.org2 = unit;}

    private Double org2Split;
    public Double getOrg2Split() {return this.org2Split;}
    public void setOrg2Split(Double percent) {this.org2Split = percent;}

    private Boolean directorEndorsement2;
    public Boolean getDirectorEndorsement2() {return this.directorEndorsement2;}
    public void setDirectorEndorsement2(Boolean endorsed) {this.directorEndorsement2 = endorsed;}

    private LocalDate directorEndorsement2Date;
    public LocalDate getDirectorEndorsement2Date() {return this.directorEndorsement2Date;}
    public void setDirectorEndorsement2Date(LocalDate date) {this.directorEndorsement2Date = date;}
}
