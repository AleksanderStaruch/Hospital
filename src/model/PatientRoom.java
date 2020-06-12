package model;

import model.actors.Patient;
import model.building.Room;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "PatientRoom")
public class PatientRoom {
    private long id;
    private Room room;
    private Patient patient;
    private LocalDate fromm;
    private LocalDate to;

    public PatientRoom(){}
    public PatientRoom(Room room, Patient patient, LocalDate fromm, LocalDate to) throws Exception {
        this.setRoom(room);
        this.setPatient(patient);
        this.setFromm(fromm);
        this.setTo(to);


        room.addStay(this);
        patient.addStay(this);
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() { return id; }
    private void setId(long id) { this.id = id; }

    @ManyToOne()
    public Room getRoom() { return room; }
    public void setRoom(Room room) {
        if(room == null){throw new NullPointerException("Room field cannot be empty.");}
        this.room = room;
    }

    @ManyToOne()
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) {
        if(patient == null){throw new NullPointerException("Patient field cannot be empty.");}
        this.patient = patient;
    }

    @Basic(optional = false)
    public LocalDate getFromm() { return fromm; }
    public void setFromm(LocalDate fromm) {
        if(fromm == null){throw new NullPointerException("Date from field cannot be empty.");}
        this.fromm = fromm;
    }

    @Basic()
    public LocalDate getTo() { return to; }
    public void setTo(LocalDate to) { this.to = to; }


    private void finish(){
        this.setTo(LocalDate.now());
    }

    @Override
    public String toString() {
        return "PatientRoom{" +
                "id=" + id +
                ", room=" + room +
                ", patient=" + patient +
                ", fromm=" + fromm +
                ", to=" + to +
                '}';
    }
}

