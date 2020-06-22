package model.actors;

import model.Address;
import model.MedicalWorker;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "Doctor")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Doctor extends MedicalWorker {
    private String specialization;

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

    @Override
    public String toString() {
        return "Doctor{" + super.toString() +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}

