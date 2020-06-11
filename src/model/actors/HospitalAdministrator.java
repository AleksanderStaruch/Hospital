package model.actors;

import model.Address;
import model.building.Hospital;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "HospitalAdministrator")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class HospitalAdministrator extends Surgeon{
    private LocalDate dateOfTakingOffice;

    private Hospital hospital;

    public HospitalAdministrator() {}
    public HospitalAdministrator(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, String login, String password, Double salary, String education, Degree degree, String specialization, Type type, LocalDate dateOfTakingOffice) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail, login, password, salary, education, degree, specialization, type);
        this.setDateOfTakingOffice(dateOfTakingOffice);
    }

    //    TODO implement performMedicalTreatment
    public void performMedicalTreatment() {
        System.out.println("Hospital admin "+getName()+" "+getSurname()+" is performing medical treatment");
    }

    @Basic(optional = false)
    public LocalDate getDateOfTakingOffice() { return dateOfTakingOffice; }
    public void setDateOfTakingOffice(LocalDate dateOfTakingOffice) {
        if(dateOfTakingOffice == null){throw new NullPointerException("Date of taking office field cannot be empty.");}
        this.dateOfTakingOffice = dateOfTakingOffice;
    }

    @OneToOne(mappedBy = "hospitalAdministrator",cascade = CascadeType.ALL, orphanRemoval = true)
    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }

    @Override
    public String toString() {
        return "HospitalAdministrator{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", surname='" + this.getSurname() + '\'' +
                ", PESEL='" + this.getPESEL() + '\'' +
                ", address=" + this.getAddress() +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", sex=" + (this.getSex()? "male": "female") +
                ", birthDate=" + this.getBirthDate() +
                ", mail='" + this.getMail() + '\''+
                ", salary=" + this.getSalary() +
                ", education='" + this.getEducation() + '\''+
                ", degree=" + this.getDegree() +
                ", specialization='" + this.getSpecialization() + '\'' +
                ", dateOfTakingOffice=" + dateOfTakingOffice +
                ", type=" + this.getType() +'}';
    }
}

