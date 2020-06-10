package model.actors;

import model.Address;
import model.Worker;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity(name = "Receptionist")
public class Receptionist extends Worker {

    private List<String> languages;//powtarzalny, opcjonalny

    public Receptionist(){}
    public Receptionist(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, String login, String password, Double salary, String education, List<String> languages) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail, login, password, salary, education);
        this.languages = languages;
    }

    @ElementCollection
    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) {
        if(languages == null){throw new NullPointerException("Languages field cannot be empty.");}
        this.languages = languages;
    }

//    TODO implement registerPatient
    public void registerPatient(Patient newPatient){
        System.out.println(newPatient+" registered.");
    }

    @Override
    public String toString() {
        return "Receptionist{" + super.toString() +
                ", languages=" + languages +
                '}';
    }
}
