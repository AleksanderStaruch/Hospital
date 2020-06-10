package model.actors;

import model.Address;
import model.ISurgeon;
import model.MedicalWorker;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity(name = "Surgeon")
public class Surgeon extends MedicalWorker implements ISurgeon {
    private ISurgeon.Type type;

    public Surgeon() {}
    public Surgeon(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, String login, String password, Double salary, String education, Degree degree, Type type) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail, login, password, salary, education, degree);
        this.setType(type);
    }

    @Enumerated
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

//    TODO implement performMedicalTreatment
    protected void performMedicalTreatment() {
        System.out.println("Surgeon "+getName()+" "+getSurname()+" is performing medical treatment");
    }

    @Override
    public String toString() {
        return "Surgeon{" + super.toString() +
                ", type=" + type +
                '}';
    }
}
