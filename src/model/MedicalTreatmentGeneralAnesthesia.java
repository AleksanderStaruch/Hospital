package model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "MedicalTreatmentGeneralAnesth")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MedicalTreatmentGeneralAnesthesia extends MedicalTreatment{
    private Integer wakeUpTime;
    private Integer durationOfTheProcedure;

    public MedicalTreatmentGeneralAnesthesia() { }
    public MedicalTreatmentGeneralAnesthesia(String name, String description,State state, Type type, Integer wakeUpTime, Integer duration) {
        super(name, description,state,type);
        this.wakeUpTime = wakeUpTime;
        this.durationOfTheProcedure = duration;
    }

    @Basic
    public Integer getWakeUpTime() { return wakeUpTime; }
    public void setWakeUpTime(Integer wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
    }

    @Basic
    public Integer getDuration() { return durationOfTheProcedure; }
    public void setDuration(Integer durationOfTheProcedure) {
        this.durationOfTheProcedure = durationOfTheProcedure;
    }

    @Override
    public String toString() {
        return "MedicalTreatmentGeneralAnesthesia{" + super.toString() +
                ",wakeUpTime=" + wakeUpTime +
                ", durationOfTheProcedure=" + durationOfTheProcedure +
                '}';
    }
}
