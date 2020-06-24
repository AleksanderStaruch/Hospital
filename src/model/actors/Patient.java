package model.actors;

import model.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Patient")
public class Patient extends Person {

    private LocalDate registrationDate;

    private List<PatientRoom> patientRooms;
    private List<MedicalTreatment> medicalTreatments;

    private List<Document> documentList = new ArrayList<>();

    public Patient() {}
    public Patient(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, LocalDate registrationDate) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail);
        this.setRegistrationDate(registrationDate);
        patientRooms = new ArrayList<>();
    }


    public void addStay(PatientRoom stay) throws Exception {
        try {
            PatientRoom lastStay = patientRooms.get(patientRooms.size() - 1);
            if(lastStay.getTo() != null){
                this.getPatientRooms().add(stay);
            }else{
                throw new Exception("Patient hasn't finish last stay in room.");
            }
        }catch(IndexOutOfBoundsException ex){
//            ex.printStackTrace();
        }
        this.getPatientRooms().add(stay);
    }

    @Basic(optional = false)
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) {
        if(registrationDate == null){throw new NullPointerException("Registration date field cannot be empty.");}
        if(registrationDate.isBefore(this.getBirthDate())){throw new NullPointerException("Registration date is before patient birth date.");}
        this.registrationDate = registrationDate;
    }

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<PatientRoom> getPatientRooms() { return patientRooms; }
    public void setPatientRooms(List<PatientRoom> patientRooms) { this.patientRooms = patientRooms; }

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<MedicalTreatment> getMedicalTreatments() { return medicalTreatments; }
    public void setMedicalTreatments(List<MedicalTreatment> medicalTreatments) { this.medicalTreatments = medicalTreatments; }

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Document> getDocumentList() { return documentList; }
    public void setDocumentList(List<Document> documentList) { this.documentList = documentList; }

    @Transactional
    public PatientRoom lastPatientRooms() {
        var tmpList = this.getPatientRooms();
        System.out.println(tmpList);
        if (tmpList.size() > 0){
            return tmpList.get(tmpList.size() - 1);
        }else{
            return null;
        }
     }

    @Override
    public String toString() {
        return "Patient{" + super.toString() +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
