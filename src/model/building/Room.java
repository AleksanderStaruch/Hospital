package model.building;

import model.PatientRoom;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name="Room")
public class Room implements Serializable {
    private static Set<Integer> roomsNumbers = new HashSet<>();

    private long id;
    private Department department;
    private Integer roomNumber;
    private Integer bedsCount;

    private List<PatientRoom> patientRooms;

    public Room(){}
    private Room(Department department, Integer roomNumber, Integer bedsCount) throws Exception {
        this.setDepartment(department);
        this.setRoomNumber(roomNumber);
        this.setBedsCount(bedsCount);

        patientRooms = new ArrayList<>();
    }

    public static Room createRoom(Department department, Integer roomNumber, Integer bedsCount)throws Exception{
        if(department == null){throw new NullPointerException("Department doesn't exist.");}

        Room room = new Room(department, roomNumber, bedsCount);
        department.getRooms().add(room);
        return room;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() { return id; }
    private void setId(long id) { this.id = id; }

    @Basic
    public Integer getRoomNumber() { return roomNumber; }
    public void setRoomNumber(Integer roomNumber) throws Exception {
        if(roomNumber == null){throw new NullPointerException("Room number field cannot be empty.");}
        if(roomsNumbers.contains(this.roomNumber)){throw new Exception("There is already a room number with this number.");}
        roomsNumbers.add(this.roomNumber);
        this.roomNumber = department.getFloorNumber()*100+roomNumber;
    }

    @Basic
    public Integer getBedsCount() { return bedsCount; }
    public void setBedsCount(Integer bedsCount) throws Exception {
        if(bedsCount == null){throw new NullPointerException("Beds count field cannot be empty.");}
        if(!(bedsCount >= 1 && bedsCount <=8)){throw new Exception("Beds count must be between 1 and 8 beds.");}
        this.bedsCount = bedsCount;
    }

    @Transient
    public Integer getOccupiedBedsCount() { return patientRooms.size(); }

    @Transient
    public int getFreeBedsCount() { return bedsCount-getOccupiedBedsCount(); }

    @ManyToOne
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) {
        if(department == null){throw new NullPointerException("Department field cannot be empty.");}
        this.department = department;
    }

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<PatientRoom> getPatientRooms() { return patientRooms; }
    public void setPatientRooms(List<PatientRoom> patientRooms) { this.patientRooms = patientRooms; }

    @Override
    public String toString() {
        return "Room{" +
                "department=" + department +
                ", roomNumber=" + roomNumber +
                ", bedsCount=" + bedsCount +'}';
    }

    public void addStay(PatientRoom stay) throws Exception {
        if(getFreeBedsCount() == 0){throw new Exception("No free bed in this room");}
        this.getPatientRooms().add(stay);
    }

}

