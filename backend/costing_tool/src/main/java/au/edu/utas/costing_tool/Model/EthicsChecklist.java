package au.edu.utas.costing_tool.Model;

import javax.persistence.Embeddable;

@Embeddable
public class EthicsChecklist
{
    private Boolean humanMedical;
    public Boolean getHumanMedical() {return this.humanMedical;}
    public void setHumanMedical(Boolean involves) {this.humanMedical = involves;}

    private Integer humanMedicalRef;
    public Integer getHumanMedicalRef() {return this.humanMedicalRef;}
    public void setHumanMedicalRef(Integer ref) {this.humanMedicalRef = ref;}

    private Boolean humanSocialScience;
    public Boolean getHumanSocialScience() {return this.humanSocialScience;}
    public void setHumanSocialScience(Boolean involves) {this.humanSocialScience = involves;}

    private Integer humanSocialScienceRef;
    public Integer getHumanSocialScienceRef() {return this.humanSocialScienceRef;}
    public void setHumanSocialScienceRef(Integer ref) {this.humanSocialScienceRef = ref;}

    private Boolean animals;
    public Boolean getAnimals() {return this.animals;}
    public void setAnimals(Boolean involves) {this.animals = involves;}

    private Integer animalsRef;
    public Integer getAnimalsRef() {return this.animalsRef;}
    public void setAnimalsRef(Integer ref) {this.animalsRef = ref;}

    private Boolean gmo;
    public Boolean getGMO() {return this.gmo;}
    public void setGMO(Boolean involves) {this.gmo = involves;}

    private Integer gmoRef;
    public Integer getGMORef() {return this.gmoRef;}
    public void setGMORef(Integer ref) {this.gmoRef = ref;}

    private Boolean radiation;
    public Boolean getRadiation() {return this.radiation;}
    public void setRadiation(Boolean involves) {this.radiation = involves;}

    private Integer radiationRef;
    public Integer getRadiationRef() {return this.radiationRef;}
    public void setRadiationRef(Integer ref) {this.radiationRef = ref;}

    private Boolean carcinogenTeratogen;
    public Boolean getCarcinogenTeratogen() {return this.carcinogenTeratogen;}
    public void setCarcinogenTeratogen(Boolean involves) {this.carcinogenTeratogen = involves;}

    private Integer carcinogenTeratogenRef;
    public Integer getCarcinogenTeratogenRef() {return this.carcinogenTeratogenRef;}
    public void setCarcinogenTeratogenRef(Integer ref) {this.carcinogenTeratogenRef = ref;}
}
