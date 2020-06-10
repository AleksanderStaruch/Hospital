package model.actors;

import model.Address;
import model.MedicalWorker;
import model.building.Department;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Nurse")
public class Nurse extends MedicalWorker {
    private List<String> certificates;//powtarzalny, opcjonalny

    private Department department;

    public Nurse(){}
    public Nurse(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, String login, String password, Double salary, String education, Degree degree, List<String> certificates) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail, login, password, salary, education, degree);
        this.setCertificates(certificates);
//        this.setDepartment(department);
    }

//    TODO implement performMedicalTreatment
    public void performMedicalTreatment() {
        System.out.println("Nurse "+getName()+" "+getSurname()+" is performing medical treatment");
    }

    @ElementCollection
    public List<String> getCertificates() { return certificates; }
    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    @ManyToOne
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Nurse{" + super.toString() +
                ", certificates=" + certificates + '}';
    }
}
