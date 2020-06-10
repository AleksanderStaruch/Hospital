package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity(name = "Person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
    private long id;
    private String name;
    private String surname;
    private String PESEL;
    private Address address;//zlozony
    private String phoneNumber;
    private boolean sex;//male=true,female=false
    private LocalDate birthDate;//zlozony
    private String mail;//opcjonalny

    public Person(){}

    public Person(String name, String surname, String PESEL, Address address, String phoneNumber, String mail)throws Exception {
        this.setName(name);
        this.setSurname(surname);
        this.setPESEL(PESEL);
        this.setAddress(address);
        this.birthDate = countDateOfBirth();
        this.setSex((Integer.parseInt(PESEL.substring(10,11)))%2 == 0);
        this.setPhoneNumber(phoneNumber);
        this.setMail(mail);
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() { return id; }
    private void setId(long id) {
        this.id = id;
    }

    @Basic
    public String getName() { return name; }
    public void setName(String name) {
        if(name == null){throw new NullPointerException("Name field cannot be empty.");}
        this.name = name;
    }

    @Basic
    public String getSurname() { return surname; }
    public void setSurname(String surname) {
        if(surname == null){throw new NullPointerException("Surname field cannot be empty.");}
        this.surname = surname;
    }

    @Basic
    public String getPESEL() { return PESEL; }
    public void setPESEL(String PESEL) {
        if(PESEL == null){throw new NullPointerException("PESEL field cannot be empty.");}
        this.PESEL = PESEL;
        sex = ((Integer.parseInt(PESEL.substring(9,10)))%2 == 0);
    }

    @Embedded
    public Address getAddress() { return address; }
    public void setAddress(Address address) {
        if(address == null){throw new NullPointerException("Address field cannot be empty.");}
        this.address = address;
    }

    @Basic
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber == null){throw new NullPointerException("Phone number field cannot be empty.");}
        this.phoneNumber = phoneNumber;
    }

    @Basic
    public boolean getSex() { return sex; }
    public void setSex(boolean sex) { this.sex = sex; }

    @Basic
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) {
        if(birthDate == null){throw new NullPointerException("Birth date field cannot be empty.");}
        this.birthDate = birthDate;
    }

    @Basic
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    @Transient
    public int getAge() {
        LocalDate now = LocalDate.now(); //gets localDate
        Period period = Period.between(birthDate, now);

        return period.getYears();
    }

    private LocalDate countDateOfBirth() throws Exception{
        String year = PESEL.substring(0,2);
        String mounth = PESEL.substring(2,4);
        String day = PESEL.substring(4,6);

        int y;
        int m;
        int d = Integer.parseInt(day);

        switch (mounth.charAt(0)) {
            case '8', '9' -> {
                y = Integer.parseInt("18" + year);
                m = Integer.parseInt(mounth) - 80;
            }
            case '0', '1' -> {
                y = Integer.parseInt("19" + year);
                m = Integer.parseInt(mounth);
            }
            case '2', '3' -> {
                y = Integer.parseInt("20" + year);
                m = Integer.parseInt(mounth) - 20;
            }
            case '4', '5' -> {
                y = Integer.parseInt("21" + year);
                m = Integer.parseInt(mounth) - 40;
            }
            case '6', '7' -> {
                y = Integer.parseInt("22" + year);
                m = Integer.parseInt(mounth) - 60;
            }
            default -> throw new Exception("Wrong PESEL");
        }

        return LocalDate.of(y, m, d);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", PESEL='" + PESEL + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex=" + (sex? "male": "female") +
                ", birthDate=" + birthDate +
                ", mail='" + Objects.requireNonNullElse(mail, "no data") + '\'';
    }
}
