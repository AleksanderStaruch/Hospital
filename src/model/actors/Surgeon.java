package model.actors;

import model.Address;

import javax.persistence.*;

@Entity(name = "Surgeon")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Surgeon extends Doctor{
    public enum Type{NEURO, CARDIO, THORACIC};

    private Type type;

    public Surgeon() {}
    public Surgeon(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, String login, String password, Double salary, String education, Degree degree, String specialization, Type type) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail, login, password, salary, education, degree, specialization);
        this.setType(type);
    }

    @Enumerated(value = EnumType.STRING)
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

//    TODO implement performMedicalTreatment
    public void performMedicalTreatment() {
        System.out.println("Surgeon "+getName()+" "+getSurname()+" is performing medical treatment");
    }

    @Override
    public String toString() {
        return "Surgeon{" +
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
                ", type=" + this.getType() +'}';
    }
}
