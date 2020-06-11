package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Address implements Serializable {

    private String city;
    private String codePostal;
    private String street;
    private Integer number;
    private Integer numberOfLocal;//opcjonanlny

    public Address(){}
    public Address(String city, String codePostal, String street, Integer number, Integer numberOfLocal) {
        this.setCity(city);
        this.setCodePostal(codePostal);
        this.setStreet(street);
        this.setNumber(number);
        this.setNumberOfLocal(numberOfLocal);
    }

    public Address(String city, String codePostal, String street, Integer number) {
        this.setCity(city);
        this.setCodePostal(codePostal);
        this.setStreet(street);
        this.setNumber(number);
        this.setNumberOfLocal(null);
    }

    @Basic(optional = false)
    public String getCity() { return city; }
    public void setCity(String city) {
        if(city == null){throw new NullPointerException("City field cannot be empty.");}
        this.city = city;
    }

    @Basic(optional = false)
    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) {
        if(codePostal == null){throw new NullPointerException("Code postal field cannot be empty.");}
        this.codePostal = codePostal;
    }

    @Basic(optional = false)
    public String getStreet() { return street; }
    public void setStreet(String street) {
        if(street == null){throw new NullPointerException("Street field cannot be empty.");}
        this.street = street;
    }

    @Basic(optional = false)
    public Integer getNumber() { return number; }
    public void setNumber(Integer number) {
        if(number == null){throw new NullPointerException("Number field cannot be empty.");}
        this.number = number;
    }


    @Basic
    public Integer getNumberOfLocal() {
        return numberOfLocal;
    }
    public void setNumberOfLocal(Integer numberOfLocal) {
        this.numberOfLocal = numberOfLocal;
    }

    @Override
    public String toString() {
        return  "Address{" +
                "city='" + city + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", numberOfLocal=" + Objects.requireNonNullElse(numberOfLocal, "no data") + '}';
    }
}