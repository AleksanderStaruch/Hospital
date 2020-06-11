package model.actors;

import model.Address;
import model.MedicalTreatment;
import model.PatientRoom;
import model.Person;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Patient")
public class Patient extends Person {

    private LocalDate registrationDate;

    private List<PatientRoom> patientRooms;
    private List<MedicalTreatment> medicalTreatments;

    public Patient() {}
    public Patient(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, LocalDate registrationDate) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail);
//        TODO birthday > registrationDate
        this.setRegistrationDate(registrationDate);
        patientRooms = new ArrayList<>();
    }

    /**
     * dont make another stay when patient doesnt finished last one
     * @param stay
     * @throws Exception
     */
    public void addStay(PatientRoom stay) throws Exception {
        try {
            PatientRoom lastStay = patientRooms.get(patientRooms.size() - 1);
            if(lastStay.getTo() != null){
                this.getPatientRooms().add(stay);
            }else{
                throw new Exception("Patient hasn't finish last stay in room.");
            }
        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
        this.getPatientRooms().add(stay);
    }

    @Basic
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) {
        if(registrationDate == null){throw new NullPointerException("Password field cannot be empty.");}
        this.registrationDate = registrationDate;
    }

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<PatientRoom> getPatientRooms() { return patientRooms; }
    public void setPatientRooms(List<PatientRoom> patientRooms) { this.patientRooms = patientRooms; }

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<MedicalTreatment> getMedicalTreatments() { return medicalTreatments; }
    public void setMedicalTreatments(List<MedicalTreatment> medicalTreatments) { this.medicalTreatments = medicalTreatments; }

    @Override
    public String toString() {
        return "Patient{" + super.toString() +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
