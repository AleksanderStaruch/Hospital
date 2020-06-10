import model.actors.*;
import model.building.*;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

    private static void create(Session session) throws Exception {

        Hospital hospital1;
        Hospital hospital2;

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




        for(int i =0;i<50;i++){
            try{
                Patient patient = GeneratorDanych.generatorPatient(new Random().nextBoolean());
                session.save(patient);
                session.beginTransaction();
                session.getTransaction().commit();
            }catch (Exception ex){ex.printStackTrace();}
        }

        for(int i =0;i<10;i++){
        try{
            Receptionist receptionist = GeneratorDanych.generatorReceptionist(new Random().nextBoolean());
            session.save(receptionist);
            session.beginTransaction();
            session.getTransaction().commit();
        }catch (Exception ex){ex.printStackTrace();}
        }

        for(int i =0;i<20;i++){
            try{
                Nurse nurse = GeneratorDanych.generatorNurse(new Random().nextBoolean());
                session.save(nurse);
                session.beginTransaction();
                session.getTransaction().commit();
            }catch (Exception ex){ex.printStackTrace();}
        }

        for(int i =0;i<20;i++){
            try{
                Doctor doctor = GeneratorDanych.generatorDoctor(new Random().nextBoolean());
                session.save(doctor);
                session.beginTransaction();
                session.getTransaction().commit();
            }catch (Exception ex){ex.printStackTrace();}
        }

        for(int i =0;i<10;i++){
            try{
                Surgeon surgeon = GeneratorDanych.generatorSurgeon(new Random().nextBoolean());
                session.save(surgeon);
                session.beginTransaction();
                session.getTransaction().commit();
            }catch (Exception ex){ex.printStackTrace();}
        }

        for(int i =0;i<2;i++){
            try{
                HospitalAdministrator hospitalAdministrator = GeneratorDanych.generatorAdmin(new Random().nextBoolean());
                session.save(hospitalAdministrator);
                session.beginTransaction();
                session.getTransaction().commit();
            }catch (Exception ex){ex.printStackTrace();}
        }

    }

    private static void print(Session session, String table){
        List<Worker> list = session.createQuery("from "+table).list();
        for(Worker person :list){
            System.out.println(person.getLogin());
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
        final Session session = getSession();
        try {
            create(session);
//            print(session);
            print(session,"Worker");
//

//            List<Hospital> hospitalList = session.createQuery("from Hospital ").list();
//            List<MedicalWorker> medicalWorkerList = session.createQuery("from MedicalWorker ").list();


//
//            System.out.println(hospitalList);
//            System.out.println(medicalWorkerList);
//            System.out.println(patientList);
//
//            System.out.println(hospitalList.get(0));
//            System.out.println(hospitalList.get(1));

//
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
        } finally {
            session.close();
        }
    }
}