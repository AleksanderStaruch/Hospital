import model.actors.*;
import model.building.*;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final SessionFactory ourSessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    private static List<Person> create(Session session) throws Exception {

        Hospital hospital;

        Department department1;
        Department department2;
        Department department3;

        Department department4;
        Department department5;
        Department department6;

        Room room1;
        Room room2;
        Room room3;
        Room room4;
        Room room5;
        Room room6;
        Room room7;
        Room room8;

        Room room9;
        Room room10;
        Room room11;
        Room room12;
        Room room13;
        Room room14;
        Room room15;
        Room room16;
        List<Person> list = new ArrayList<>();

        for(int i =0;i<50;i++){
            try{
                Patient patient = GeneratorDanych.generatorPatient(new Random().nextBoolean());
                list.add(patient);
//                System.out.println(patient.getPESEL());
                session.save(patient);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<10;i++){
            try{
                Receptionist receptionist = GeneratorDanych.generatorReceptionist(new Random().nextBoolean());
                list.add(receptionist);
//                System.out.println(receptionist.getPESEL());
                session.save(receptionist);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<20;i++){
            try{
                Nurse nurse = GeneratorDanych.generatorNurse(new Random().nextBoolean());
                list.add(nurse);
//                System.out.println(nurse.getPESEL());
                session.save(nurse);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<20;i++){
            try{
                Doctor doctor = GeneratorDanych.generatorDoctor(new Random().nextBoolean());
                list.add(doctor);
//                System.out.println(doctor.getPESEL());
                session.save(doctor);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<10;i++){
            try{
                Surgeon surgeon = GeneratorDanych.generatorSurgeon(new Random().nextBoolean());
                list.add(surgeon);
//                System.out.println(surgeon.getPESEL());
                session.save(surgeon);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<1;i++){
            try{
                HospitalAdministrator hospitalAdministrator = GeneratorDanych.generatorAdmin(new Random().nextBoolean());
                list.add(hospitalAdministrator);
//                System.out.println(hospitalAdministrator.getPESEL());
                session.save(hospitalAdministrator);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

//        session.beginTransaction();
//        session.getTransaction().commit();
        return list;
    }

    private static void print(Session session, String table){
        List<Person> list = session.createQuery("from "+table).list();
        for(Person person :list){
            System.out.println(person.getPESEL());
        }
    }

    private static void print(Session session){
        List<Patient> patientList = session.createQuery("from Patient ").list();
        List<Receptionist> receptionists = session.createQuery("from Receptionist ").list();
        List<Nurse> nurseList = session.createQuery("from Nurse ").list();
        List<Doctor> doctorList = session.createQuery("from Doctor ").list();
        List<Surgeon> surgeonList= session.createQuery("from Surgeon ").list();
        List<HospitalAdministrator> administratorList = session.createQuery("from HospitalAdministrator ").list();

        System.out.println("P");
        for(Patient patient :patientList){
            System.out.println(patient);
        }

        System.out.println("R");
        for(Receptionist p :receptionists){
            System.out.println(p);
        }

        System.out.println("N");
        for(Nurse p :nurseList){
            System.out.println(p);
        }

        System.out.println("D");
        for(Doctor p :doctorList){
            System.out.println(p);
        }

        System.out.println("S");
        for(Surgeon p :surgeonList){
            System.out.println(p);
        }

        System.out.println("A");
        for(HospitalAdministrator p :administratorList){
            System.out.println(p);
        }

    }

    public static void main(final String[] args) throws Exception {
        try (Session session = getSession()) {
            var list = create(session);
            for (Person p : list) {
                System.out.println(p);
            }
            System.out.println("--------------------");

//            for (Person p : list) {
//                System.out.println(p.getPESEL());
//            }
//            print(session, "Person");
//            print(session,"Worker");
//            System.err.println("--------------------");
//            System.out.println(GeneratorDanych.femalePESELid);
//            System.out.println(GeneratorDanych.malePESELid);
            System.out.println("--------------------");
//            print(session);


//            List<Hospital> hospitalList = session.createQuery("from Hospital ").list();
//            List<MedicalWorker> medicalWorkerList = session.createQuery("from MedicalWorker ").list();


//
//            System.out.println(hospitalList);
//            System.out.println(medicalWorkerList);
//            System.out.println(patientList);
//
//            System.out.println(hospitalList.get(0));
//            System.out.println(hospitalList.get(1));


        }
    }
}