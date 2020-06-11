package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Worker")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Worker extends Person {
    private static final int minWage = 1500;

    private String login;
    private String password;
    private Double salary = 0.0;
    private String education;

    private static List<String> logins = new ArrayList<>();

    public Worker(){}
    public Worker(String name, String surname, String PESEL, Address address, String phoneNumber, String mail, String login, String password, Double salary, String education) throws Exception {
        super(name, surname, PESEL, address, phoneNumber, mail);
        this.setLogin(login);
        this.setPassword(password);
        this.setSalary(salary);
        this.setEducation(education);
    }

    @Basic
    public double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) throws Exception {
        if(salary == null){throw new NullPointerException("Salary field cannot be empty.");}
        if(salary >= minWage){
            if(salary>this.salary)
                this.salary = salary;
        }else{
            throw new Exception("Salary must be equale or greater from minimum wage ");
        }
    }

    @Basic
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        if(education == null){throw new NullPointerException("Education field cannot be empty.");}
        this.education = education;
    }

    @Basic
    public String getLogin() { return login; }
    public void setLogin(String login) throws Exception {
        String pattern = "^(?=.*[a-z])(?=\\S+$).{2,}$";
        if(login == null){throw new NullPointerException("Login field cannot be empty.");}
        if(!login.matches(pattern)){throw new Exception("Login is wrong to login policy ( "+login+", "+getName()+" "+getSurname()+" ).");}
//        if(logins.contains(login)){throw new Exception("There is someone with this login ( "+login+", "+getName()+" "+getSurname()+" ).");}
//        logins.add(login);
        this.login = login;
    }

    @Basic
    public String getPassword() { return password; }
    public void setPassword(String password) throws Exception {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{4,}$";
        if(password == null){throw new NullPointerException("Password field cannot be empty.");}
        if(!password.matches(pattern)){throw new Exception("Password is wrong to password policy.");}
        this.password = password;
    }


    public static List<String> getLogins() { return logins; }
//    TODO query to set list every time program starts
    public static void setLogins(List<String> logins) { Worker.logins = logins; }

    @Override
    public String toString() {
        return super.toString() +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", education='" + education + '\'';
    }
}
