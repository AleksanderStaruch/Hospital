package model;

import model.actors.Doctor;
import model.actors.Patient;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Document")
public class Document {
    enum Type{DISCHARGE,PRESCRIPTION,DEATHCERTIFICATE}

    private long id;
    private LocalDate date;
    private String description;
    private Type type;

    Doctor doctor;
    Patient patient;

    public Document() { }
    public Document(LocalDate date, String description, Type type) {
        this.date = date;
        this.description = description;
        this.type = type;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() { return id; }
    private void setId(long id) {
        this.id = id;
    }

    @Basic(optional = false)
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Basic(optional = false)
    public String getDescription() { return description; }
    public void setDescription(String opis) {
        this.description = description;
    }

    @Enumerated
    public Type getType() { return type; }
    public void setType(Type type) {
        this.type = type;
    }

    @ManyToOne
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    @ManyToOne
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
