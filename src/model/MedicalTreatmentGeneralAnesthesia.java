package model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "MedicalTreatmentGeneralAnesthesia")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MedicalTreatmentGeneralAnesthesia extends MedicalTreatment{
    private Integer wakeUpTime;
    private Integer durationOfTheProcedure;

    public MedicalTreatmentGeneralAnesthesia() { }
    public MedicalTreatmentGeneralAnesthesia(String name, String description, Double cost, Type type, Integer wakeUpTime, Integer durationOfTheProcedure) {
        super(name, description, cost, type);
        this.wakeUpTime = wakeUpTime;
        this.durationOfTheProcedure = durationOfTheProcedure;
    }

    @Basic
    public Integer getWakeUpTime() { return wakeUpTime; }
    public void setWakeUpTime(Integer wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
    }

    @Basic
    public Integer getDurationOfTheProcedure() { return durationOfTheProcedure; }
    public void setDurationOfTheProcedure(Integer durationOfTheProcedure) {
        this.durationOfTheProcedure = durationOfTheProcedure;
    }
}
