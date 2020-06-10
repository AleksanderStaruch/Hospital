package model.building;

import model.actors.Nurse;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="Department")
public class Department {
    public enum Type{GENERAL_SURGERY, INTENSIVE_CARE,CARDIOLOGY,PSYCHIATRIC}
    private long id;
    private Hospital hospital;
    private Type type;
    private Integer floorNumber;

    private Set<Room> rooms;
    private static HashSet<Room> allRooms = new HashSet<>();

    private Set<Nurse> nurses;
//    private static HashSet<Nurse> allNurses = new HashSet<>();
//    private Nurse mainNurse;

    public Department(){}
    public Department(Hospital hospital, Type type, Integer floorNumber) {
        this.setHospital(hospital);
        this.setType(type);
        this.setFloorNumber(floorNumber);

        rooms = new HashSet<>();
        nurses = new HashSet<>();
    }

    public static Department createDepartment(Hospital hospital, Type type, Integer floorNumber)throws Exception{
        if(hospital == null){throw new NullPointerException("Hospital doesn't exist.");}

        Department department = new Department(hospital, type, floorNumber);
        hospital.getDepartments().add(department);
        return department;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() { return id; }
    private void setId(long id) { this.id = id; }

    @Enumerated
    public Type getType() { return type; }
    public void setType(Type type) {
        if(type == null){throw new NullPointerException("Type field cannot be empty.");}
        this.type = type;
    }

    @Basic
    public Integer getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Integer floorNumber) {
        if(floorNumber == null){throw new NullPointerException("Floor number field cannot be empty.");}
        this.floorNumber = floorNumber;
    }

    @ManyToOne
    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) {
        if(hospital == null){throw new NullPointerException("Hospital doesn't exist.");}
        this.hospital = hospital;
    }

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Room> getRooms() { return rooms; }
    public void setRooms(Set<Room> rooms) { this.rooms = rooms; }

    public void addRoom(Room room) throws Exception{
        if(this.getRooms().contains(room)){throw new Exception("Room already is connected to this department.");}
        if(allRooms.contains(room)){throw new Exception("Room already is connected to another department.");}

        this.getRooms().add(room);
        allRooms.add(room);
    }

    public static HashSet<Room> getAllRooms() {
        return allRooms;
    }
//    TODO query to set list every time program starts
    public static void setAllRooms(HashSet<Room> allRooms) { Department.allRooms = allRooms; }

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Nurse> getNurses() { return nurses; }
    public void setNurses(Set<Nurse> nurses) { this.nurses = nurses; }


//    public void addNurse(Nurse nurse, boolean r) throws Exception{
//        if(nurses.contains(nurse)){throw new Exception("Nurse already is connected to this department.");}
//        nurses.add(nurse);
//        nurse.setDepartment(this,false, !r);
//        //if(allNurses.contains(nurse)){throw new Exception("Nurse already is connected to another department.");}
//        //allNurses.add(nurse);
//    }
//
//    public List<Nurse> getNurses() {
//        return nurses;
//    }
//    public static HashSet<Nurse> getAllNurses() {
//        return allNurses;
//    }
//    public Nurse getMainNurse() {
//        return mainNurse;
//    }
//    public void setMainNurse(Nurse nurse, boolean r) throws Exception {
//        if(nurses.size()==0){{throw new Exception("Surgeon doesn't have staff");}}
//        this.mainNurse= nurse;
//        nurse.setDepartment(this,true, r);
//    }

    @Override
    public String toString() {
        return "Department{"+
                "type=" + type +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
