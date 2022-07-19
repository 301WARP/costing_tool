package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import java.time.LocalDate;

import javax.persistence.Embeddable;


// TODO(Andrew): Consider creating new table for DirectorEndorsement
@Embeddable
public class DirectorEndorsement
{
    // =========================================================================
    // Properties
    // =========================================================================

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


    // =========================================================================
    // Constructors
    // =========================================================================

    public DirectorEndorsement() {}

    public DirectorEndorsement( String org1,
                                Double org1Split,
                                Boolean directorEndorsement1,
                                LocalDate directorEndorsement1Date,
                                String org2,
                                Double org2Split,
                                Boolean directorEndorsement2,
                                LocalDate directorEndorsement2Date)
    {
        this.setOrg1(org1);
        this.setOrg1Split(org1Split);
        this.setDirectorEndorsement1(directorEndorsement1);
        this.setDirectorEndorsement1Date(directorEndorsement1Date);

        this.setOrg2(org2);
        this.setOrg2Split(org2Split);
        this.setDirectorEndorsement2(directorEndorsement2);
        this.setDirectorEndorsement2Date(directorEndorsement2Date);
    }
}