package model;

import model.actors.Patient;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.*;

@Entity(name = "MedicalTreatment")
public abstract class MedicalTreatment {
    public enum State{PLANNED,DURING,EXECUTED,CANCELLED}
    public enum Type{NORMAL,MINIMALLY_INVASIVE,INVASIVE,DANGEROUS}

    private long id;
    private String name;
    private String description;
    private Double cost;
    private State state;
    private Type type;

    private Patient patient;

    private List<MedicalWorkerTreatment> medicalWorkerTreatments;

    public MedicalTreatment(){}
    public MedicalTreatment(String name, String description, Double cost, State state, Type type) {
        this.setName(name);
        this.setDescription(description);
        this.setCost(cost);
        this.setState(state);
        this.setType(type);

        medicalWorkerTreatments = new ArrayList<>();
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() { return id; }
    private void setId(long id) { this.id = id; }

    @Basic(optional = false)
    public String getName() { return name; }
    public void setName(String name) {
        if(name == null){throw new NullPointerException("Name field cannot be empty.");}
        this.name = name;
    }

    @Basic(optional = false)
    public String getDescription() { return description; }
    public void setDescription(String text) {
        if(text == null){throw new NullPointerException("Description field cannot be empty.");}
        this.description = text;
    }

    @Basic(optional = false)
    public Double getCost() { return cost; }
    public void setCost(Double cost) {
        if(cost == null){throw new NullPointerException("Cost field cannot be empty.");}
        this.cost = cost;
    }

    @Enumerated(value = EnumType.STRING)
    public State getState() { return state; }
    public void setState(State state) {
        if(state == null){throw new NullPointerException("State field cannot be empty.");}
        this.state = state;
    }

    @Enumerated(value = EnumType.STRING)
    public Type getType() { return type; }
    public void setType(Type type) {
        if(type == null){throw new NullPointerException("Type field cannot be empty.");}
        this.type = type;
    }

    @ManyToOne
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    @Override
    public String toString() {
        return "MedicalTreatment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", state=" + state +
                ", type=" + type +
                '}';
    }
}
