package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "MedicalWorkerTreatment")
public class MedicalWorkerTreatment {
    private long id;
    private MedicalWorker medicalWorker;
    private MedicalTreatment medicalTreatment;
    private LocalDateTime fromm;
    private LocalDateTime to;

    public MedicalWorkerTreatment() {}
    public MedicalWorkerTreatment(MedicalWorker medicalWorker, MedicalTreatment medicalTreatment, LocalDateTime fromm, LocalDateTime to) {
        this.setMedicalWorker(medicalWorker);
        this.setMedicalTreatment(medicalTreatment);
        this.setFromm(fromm);
        this.setTo(to);
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() { return id; }
    private void setId(long id) { this.id = id; }

    @ManyToOne()
    public MedicalWorker getMedicalWorker() { return medicalWorker; }
    public void setMedicalWorker(MedicalWorker medicalWorker) {
        if(medicalWorker == null){throw new NullPointerException("Medical worker field cannot be empty.");}
        this.medicalWorker = medicalWorker;
    }

    @ManyToOne()
    public MedicalTreatment getMedicalTreatment() { return medicalTreatment; }
    public void setMedicalTreatment(MedicalTreatment medicalTreatment) {
        if(medicalTreatment == null){throw new NullPointerException("Medical treatment field cannot be empty.");}
        this.medicalTreatment = medicalTreatment;
    }

    @Basic(optional = false)
    public LocalDateTime getFromm() { return fromm; }
    public void setFromm(LocalDateTime fromm) {
        if(fromm == null){throw new NullPointerException("From field cannot be empty.");}
        this.fromm = fromm;
    }

    @Basic
    public LocalDateTime getTo() { return to; }
    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "MedicalWorkerTreatment{" +
                "id=" + id +
                ", medicalWorker=" + medicalWorker +
                ", medicalTreatment=" + medicalTreatment +
                ", fromm=" + fromm +
                ", to=" + to +
                '}';
    }
}
