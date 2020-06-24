package model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MedicalTreatmentLocalAnesthesia")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MedicalTreatmentLocalAnesthesia extends MedicalTreatment{
    private String anestheticDrug;
    private List<String> bodyParts;

    public MedicalTreatmentLocalAnesthesia() { }
    public MedicalTreatmentLocalAnesthesia(String name, String description, Double cost, Type type, String anestheticDrug, List<String> bodyParts) {
        super(name, description, cost, type);
        this.anestheticDrug = anestheticDrug;
        this.bodyParts = bodyParts;
    }

    @Basic
    public String getAnestheticDrug() { return anestheticDrug; }
    public void setAnestheticDrug(String anestheticDrug) {
        this.anestheticDrug = anestheticDrug;
    }

    @ElementCollection
    public List<String> getBodyParts() { return bodyParts; }
    public void setBodyParts(List<String> bodyParts) {
        this.bodyParts = bodyParts;
    }
}
