package model.building;

import model.Address;
import model.actors.HospitalAdministrator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name="Hospital")
public class Hospital {
    private long id;
    private String name;
    private Address address;
    private String phoneNumber;

    private Set<Department> departments;
    private static HashSet<Department> allDepartments = new HashSet<>();
    private HospitalAdministrator hospitalAdministrator;

    public Hospital() { }
    public Hospital(String name, Address address, String phoneNumber) {
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);

        departments = new HashSet<>();
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() { return id; }
    private void setId(long id) { this.id = id; }

    @Basic
    public String getName() { return name; }
    public void setName(String name) {
        if(name == null){throw new NullPointerException("Name field cannot be empty.");}
        this.name = name;
    }

    @Basic
    public Address getAddress() { return address; }
    public void setAddress(Address address) {
        if(address == null){throw new NullPointerException("Address field cannot be empty.");}
        this.address = address;
    }

    @Basic
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber== null){throw new NullPointerException("Phone number field cannot be empty.");}
        this.phoneNumber = phoneNumber;
    }

    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Department> getDepartments() { return departments; }
    public void setDepartments(Set<Department> departments) { this.departments = departments; }

    @OneToOne
    public HospitalAdministrator getHospitalAdministrator() { return hospitalAdministrator; }
    public void setHospitalAdministrator(HospitalAdministrator hospitalAdministrator) {
        this.hospitalAdministrator = hospitalAdministrator;
    }

    public void addDepartment(Department department) throws Exception{
        if(this.getDepartments().contains(department)){throw new Exception("Room already is connected to this department.");}
        if(allDepartments.contains(department)){throw new Exception("Room already is connected to another department.");}

        this.getDepartments().add(department);
        allDepartments.add(department);
    }

    public static HashSet<Department> getAllDepartments() { return allDepartments; }
//    TODO query to set list every time program starts
    public static void setAllDepartments(HashSet<Department> allDepartments) { Hospital.allDepartments = allDepartments; }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", departments=" + departments +
                '}';
    }
}
