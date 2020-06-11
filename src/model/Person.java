package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Person")
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"PESEL"}))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
public abstract class Person {
    private long id;
    private String name;
    private String surname;
    private String PESEL;//unikalny
    private Address address;//zlozony
    private String phoneNumber;
    private boolean sex;//male=true,female=false
    private LocalDate birthDate;//zlozony
    private String mail;//opcjonalny

    private static List<String> PESELs = new ArrayList<>();

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

    @Basic(optional = false)
    public String getName() { return name; }
    public void setName(String name) throws Exception {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{2,15}$";
        if(name == null){throw new NullPointerException("Name field cannot be empty.");}
        if(!name.matches(pattern)){throw new Exception("Name is wrong to name policy ("+name+").");}
        this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    @Basic(optional = false)
    public String getSurname() { return surname; }
    public void setSurname(String surname) throws Exception {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{2,15}$";
        if(surname == null){throw new NullPointerException("Surname field cannot be empty.");}
        if(!surname.matches(pattern)){throw new Exception("Surname is wrong to surname policy ("+surname+").");}
        this.surname = surname.substring(0,1).toUpperCase() + surname.substring(1).toLowerCase();
    }

    @Basic(optional = false)
    public String getPESEL() { return PESEL; }
    public void setPESEL(String PESEL) throws Exception {
        String pattern = "^(?=.*[0-9]).{11}$";
        if(PESEL == null){throw new NullPointerException("PESEL field cannot be empty.");}
        if(!PESEL.matches(pattern)){throw new Exception("PESEL is wrong to PESEL policy ("+PESEL+").");}
//        TODO fix it
//        if(!isUnique(PESELs,PESEL)){throw new Exception("There is someone with this PESEL ("+PESEL+" "+name+" "+surname+").");}
//        if(PESELs.contains(PESEL)){throw new Exception("There is someone with this PESEL ("+PESEL+" "+name+" "+surname+").");}
//        PESELs.add(PESEL);
        this.PESEL = PESEL;
        sex = ((Integer.parseInt(PESEL.substring(9,10)))%2 == 0);
    }

    @Embedded
    public Address getAddress() { return address; }
    public void setAddress(Address address) {
        if(address == null){throw new NullPointerException("Address field cannot be empty.");}
        this.address = address;
    }

    @Basic(optional = false)
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) throws Exception {
        String pattern = "^(?=.*[0-9]).{9}$";
        if(phoneNumber == null){throw new NullPointerException("Phone number field cannot be empty.");}
        if(!phoneNumber.matches(pattern)){throw new Exception("Phone number is wrong to Phone number policy ("+phoneNumber+").");}
        this.phoneNumber = phoneNumber;
    }

    @Basic(optional = false)
    public boolean getSex() { return sex; }
    public void setSex(boolean sex) { this.sex = sex; }

    @Basic(optional = false)
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) {
        if(birthDate == null){throw new NullPointerException("Birth date field cannot be empty.");}
        this.birthDate = birthDate;
    }


    @Basic(optional = true)
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

    protected boolean isUnique(List<String> strings,String string){
        for(String s:strings){
            if(s.equals(string)){
                return false;
            }
        }
        System.out.println("List:"+strings);
        System.out.println("Element:"+string);
        return true;
    }

//    delete later
    public static List<String> getPESELs() {
        return PESELs;
    }

    public static void setPESELs(List<String> PESELs) {
        Person.PESELs = PESELs;
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
