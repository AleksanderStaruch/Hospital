package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MedicalWorker")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicalWorker extends Worker {
    public enum Degree {MASTER_DEGREE,DOCTOR_DEGREE,PROFESSOR_DEGREE};

    private Degree degree;

    private List<MedicalWorkerTreatment> medicalWorkerTreatments;

    public MedicalWorker(){}
    public MedicalWorker(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, String login, String password, Double salary, String education, Degree degree) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail, login, password, salary, education);
        this.setDegree(degree);

        medicalWorkerTreatments = new ArrayList<>();
    }

    @Enumerated(value = EnumType.STRING)
    public Degree getDegree(){ return degree; }
    public void setDegree(Degree degree){
        if(degree == null){throw new NullPointerException("Degree field cannot be empty.");}
        this.degree = degree;
    }

    protected abstract void performMedicalTreatment();

    @OneToMany(mappedBy = "medicalWorker",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<MedicalWorkerTreatment> getMedicalWorkerTreatments() { return medicalWorkerTreatments; }
    public void setMedicalWorkerTreatments(List<MedicalWorkerTreatment> medicalWorkerTreatments) { this.medicalWorkerTreatments = medicalWorkerTreatments; }

    @Override
    public String toString() {
        return super.toString() + ", degree=" + degree;
    }
}
