package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MedicalWorker")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicalWorker extends Worker {
    public enum Degree {MASTER_DEGREE,DOCTOR_DEGREE,PROFESSOR_DEGREE};

    private Degree degree;

//    private List<MedicalTreatment> medicalTreatmentList;

    public MedicalWorker(){}

    public MedicalWorker(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, String login, String password, Double salary, String education, Degree degree) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail, login, password, salary, education);
        this.setDegree(degree);

//        medicalTreatmentList = new ArrayList<>();
    }

    protected abstract void performMedicalTreatment();

//    public void addMedicalTreatment(MedicalTreatment treatment){
//        medicalTreatmentList.add(treatment);
//    }

//    TODO tabela posrednia
//    @ManyToMany
//    public List<MedicalTreatment> getMedicalTreatmentList() {
//        return medicalTreatmentList;
//    }
//
//    public void setMedicalTreatmentList(List<MedicalTreatment> medicalTreatmentList) {
//        this.medicalTreatmentList = medicalTreatmentList;
//    }

    @Enumerated(value = EnumType.STRING)
    public Degree getDegree(){ return degree; }
    public void setDegree(Degree degree){
        if(degree == null){throw new NullPointerException("Degree field cannot be empty.");}
        this.degree = degree;
    }

    @Override
    public String toString() {
        return super.toString() + ", degree=" + degree;
    }
}
