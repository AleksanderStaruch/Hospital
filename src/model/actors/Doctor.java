package model.actors;

import model.Address;
import model.Document;
import model.MedicalWorker;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Doctor")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Doctor extends MedicalWorker {
    private String specialization;

    private List<Document> documentList = new ArrayList<>();

    public Doctor() { }
    public Doctor(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, String login, String password, Double salary, String education, Degree degree, String specialization) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail, login, password, salary, education, degree);
        this.setSpecialization(specialization);
    }

//    TODO implement performMedicalTreatment
    public void performMedicalTreatment() {
        System.out.println("Doctor "+getName()+" "+getSurname()+" is performing medical treatment");
    }

//    TODO implement finishPatientStay
    public void finishPatientStay() {
        System.out.println("Doctor "+getName()+" "+getSurname()+" is performing medical treatment");
    }



    @Basic(optional = false)
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) {
        if(specialization == null){throw new NullPointerException("Specialization field cannot be empty.");}
        this.specialization = specialization;
    }

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Document> getDocumentList() { return documentList; }
    public void setDocumentList(List<Document> documentList) { this.documentList = documentList; }

    @Override
    public String toString() {
        return "Doctor{" + super.toString() +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}

