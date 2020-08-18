import model.actors.*;
import model.building.*;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private static void create(Session session) throws Exception {
        List<Patient> list1 = new ArrayList<>();
        List<Receptionist> list2 = new ArrayList<>();
        List<Nurse> list3 = new ArrayList<>();
        List<Doctor> list4 = new ArrayList<>();
        List<Surgeon> list5 = new ArrayList<>();
        List<HospitalAdministrator> list6 = new ArrayList<>();

        for(int i =0;i<50;i++){
            try{
                Patient patient = GeneratorDanych.generatorPatient(new Random().nextBoolean());
                list1.add(patient);
//                System.out.println(patient.getPESEL());
                session.save(patient);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<10;i++){
            try{
                Receptionist receptionist = GeneratorDanych.generatorReceptionist(new Random().nextBoolean());
                list2.add(receptionist);
//                System.out.println(receptionist.getPESEL());
                session.save(receptionist);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<20;i++){
            try{
                Nurse nurse = GeneratorDanych.generatorNurse(new Random().nextBoolean());
                list3.add(nurse);
//                System.out.println(nurse.getPESEL());
                session.save(nurse);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<20;i++){
            try{
                Doctor doctor = GeneratorDanych.generatorDoctor(new Random().nextBoolean());
                list4.add(doctor);
//                System.out.println(doctor.getPESEL());
                session.save(doctor);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<10;i++){
            try{
                Surgeon surgeon = GeneratorDanych.generatorSurgeon(new Random().nextBoolean());
                list5.add(surgeon);
//                System.out.println(surgeon.getPESEL());
                session.save(surgeon);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(int i =0;i<1;i++){
            try{
                HospitalAdministrator hospitalAdministrator = GeneratorDanych.generatorAdmin(new Random().nextBoolean());
                list6.add(hospitalAdministrator);
//                System.out.println(hospitalAdministrator.getPESEL());
                session.save(hospitalAdministrator);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        {
            Hospital hospital = new Hospital("Szpiatl",
                    new Address("Warszawa", "00-713", "ul. Figowa", 50),
                    "552896321");

            hospital.setHospitalAdministrator(list6.get(0));

            Department department1 = Department.createDepartment(hospital, Department.Type.CARDIOLOGY, 1);
            Department department2 = Department.createDepartment(hospital, Department.Type.GENERAL_SURGERY, 2);
            Department department3 = Department.createDepartment(hospital, Department.Type.INTENSIVE_CARE, 3);
            Department department4 = Department.createDepartment(hospital, Department.Type.PEDIATRIC, 4);
            Department department5 = Department.createDepartment(hospital, Department.Type.PSYCHIATRIC, 6);

            Room room1 = Room.createRoom(department1, 10, 7);
            Room room2 = Room.createRoom(department1, 11, 8);
            Room room3 = Room.createRoom(department1, 12, 8);
            Room room4 = Room.createRoom(department1, 13, 8);
            Room room5 = Room.createRoom(department2, 10, 8);
            Room room6 = Room.createRoom(department2, 11, 8);
            Room room7 = Room.createRoom(department2, 12, 8);
            Room room8 = Room.createRoom(department3, 10, 4);

            Room room9 = Room.createRoom(department3, 11, 4);
            Room room10 = Room.createRoom(department3, 12, 4);
            Room room11 = Room.createRoom(department4, 10, 6);
            Room room12 = Room.createRoom(department4, 11, 6);
            Room room13 = Room.createRoom(department4, 12, 6);
            Room room14 = Room.createRoom(department4, 13, 6);
            Room room15 = Room.createRoom(department5, 20, 8);
            Room room16 = Room.createRoom(department5, 21, 8);

            PatientRoom patientRoom1 = new PatientRoom(room1, list1.get(0), LocalDate.of(2019, 2, 20), LocalDate.of(2019, 5, 20));
            PatientRoom patientRoom2 = new PatientRoom(room2, list1.get(0), LocalDate.of(2019, 10, 20), LocalDate.of(2019, 12, 20));
            PatientRoom patientRoom3 = new PatientRoom(room3, list1.get(0), LocalDate.of(2020, 2, 20), LocalDate.of(2020, 5, 20));
            PatientRoom patientRoom4 = new PatientRoom(room1, list1.get(0), LocalDate.of(2020, 5, 23), null);
            PatientRoom patientRoom5 = new PatientRoom(room1, list1.get(1), LocalDate.of(2019, 2, 20), LocalDate.of(2020, 6, 20));
            PatientRoom patientRoom6 = new PatientRoom(room2, list1.get(2), LocalDate.of(2019, 3, 20), LocalDate.of(2020, 5, 20));
            PatientRoom patientRoom7 = new PatientRoom(room3, list1.get(2), LocalDate.of(2019, 4, 20), null);
            PatientRoom patientRoom8 = new PatientRoom(room1, list1.get(3), LocalDate.of(2019, 5, 20), null);
            PatientRoom patientRoom9 = new PatientRoom(room1, list1.get(4), LocalDate.of(2019, 6, 20), null);
            PatientRoom patientRoom10 = new PatientRoom(room2, list1.get(5), LocalDate.of(2019, 7, 20), null);
            PatientRoom patientRoom11 = new PatientRoom(room2, list1.get(6), LocalDate.of(2019, 8, 20), null);

            MedicalTreatmentLocalAnesthesia medicalTreatmentLocalAnesthesia1 = new MedicalTreatmentLocalAnesthesia("Morfologia","",MedicalTreatment.State.EXECUTED,MedicalTreatment.Type.NORMAL,"Drug nr. 1", List.of("",""));
            MedicalTreatmentLocalAnesthesia medicalTreatmentLocalAnesthesia2 = new MedicalTreatmentLocalAnesthesia("Rezonans magnetyczny","",MedicalTreatment.State.EXECUTED,MedicalTreatment.Type.NORMAL,null, List.of("klatka","głowa"));
            MedicalTreatmentLocalAnesthesia medicalTreatmentLocalAnesthesia3 = new MedicalTreatmentLocalAnesthesia("Morfologia","",MedicalTreatment.State.EXECUTED,MedicalTreatment.Type.NORMAL,"Drug nr. 2", List.of("",""));
            MedicalTreatmentLocalAnesthesia medicalTreatmentLocalAnesthesia4 = new MedicalTreatmentLocalAnesthesia("RTG","",MedicalTreatment.State.EXECUTED,MedicalTreatment.Type.NORMAL,null, List.of("klatka"));
            MedicalTreatmentLocalAnesthesia medicalTreatmentLocalAnesthesia5 = new MedicalTreatmentLocalAnesthesia("EKG","",MedicalTreatment.State.EXECUTED,MedicalTreatment.Type.NORMAL,null, List.of("klatka"));

            MedicalTreatmentLocalAnesthesia medicalTreatmentLocalAnesthesia6 = new MedicalTreatmentLocalAnesthesia("Morfologia","",MedicalTreatment.State.EXECUTED,MedicalTreatment.Type.NORMAL,null, List.of("klatka"));

//            change state to finished
            MedicalTreatmentGeneralAnesthesia medicalTreatmentGeneralAnesthesia1 = new MedicalTreatmentGeneralAnesthesia("Operacja wycięcia wyrostka","",MedicalTreatment.State.PLANNED,MedicalTreatment.Type.INVASIVE,0,0);
            MedicalTreatmentGeneralAnesthesia medicalTreatmentGeneralAnesthesia2 = new MedicalTreatmentGeneralAnesthesia("Wycięcie woreczka żółciowego","",MedicalTreatment.State.EXECUTED,MedicalTreatment.Type.INVASIVE,0,0);

            MedicalWorkerTreatment medicalWorkerTreatment1 = new MedicalWorkerTreatment(list3.get(1), medicalTreatmentLocalAnesthesia1, LocalDateTime.of(2019,2,25,12,13,14), LocalDateTime.of(2019,2,25,13,13,14));
            MedicalWorkerTreatment medicalWorkerTreatment2 = new MedicalWorkerTreatment(list3.get(2), medicalTreatmentLocalAnesthesia2, LocalDateTime.of(2019,3,25,12,13,14), LocalDateTime.of(2019,3,25,13,13,14));

            MedicalWorkerTreatment medicalWorkerTreatment3 = new MedicalWorkerTreatment(list3.get(3), medicalTreatmentLocalAnesthesia3, LocalDateTime.of(2020,6,24,12,13,14), LocalDateTime.of(2020,6,24,12,33,14));
            MedicalWorkerTreatment medicalWorkerTreatment4 = new MedicalWorkerTreatment(list3.get(1), medicalTreatmentLocalAnesthesia4, LocalDateTime.of(2020,6,24,15,13,14), LocalDateTime.of(2020,6,24,16,13,14));
            MedicalWorkerTreatment medicalWorkerTreatment5 = new MedicalWorkerTreatment(list3.get(2), medicalTreatmentLocalAnesthesia5, LocalDateTime.of(2020,6,24,18,13,14), LocalDateTime.of(2020,6,24,18,33,14));

            MedicalWorkerTreatment medicalWorkerTreatment12 = new MedicalWorkerTreatment(list3.get(5), medicalTreatmentLocalAnesthesia6, LocalDateTime.of(2020,6,24,18,13,14), LocalDateTime.of(2020,6,24,18,33,14));

            MedicalWorkerTreatment medicalWorkerTreatment6 = new MedicalWorkerTreatment(list3.get(4), medicalTreatmentGeneralAnesthesia1, LocalDateTime.of(2019,7,25,12,13,14), LocalDateTime.of(2019,7,25,15,13,14));
            MedicalWorkerTreatment medicalWorkerTreatment7 = new MedicalWorkerTreatment(list4.get(4), medicalTreatmentGeneralAnesthesia1, LocalDateTime.of(2019,7,25,12,13,14), LocalDateTime.of(2019,7,25,15,13,14));
            MedicalWorkerTreatment medicalWorkerTreatment8 = new MedicalWorkerTreatment(list5.get(4), medicalTreatmentGeneralAnesthesia1, LocalDateTime.of(2019,7,25,12,13,14), LocalDateTime.of(2019,7,25,15,13,14));

            MedicalWorkerTreatment medicalWorkerTreatment9 = new MedicalWorkerTreatment(list3.get(5), medicalTreatmentGeneralAnesthesia2, LocalDateTime.of(2020,5,25,12,13,14), LocalDateTime.of(2020,5,25,20,13,14));
            MedicalWorkerTreatment medicalWorkerTreatment10 = new MedicalWorkerTreatment(list4.get(5), medicalTreatmentGeneralAnesthesia2, LocalDateTime.of(2020,5,25,12,13,14), LocalDateTime.of(2020,5,25,20,13,14));
            MedicalWorkerTreatment medicalWorkerTreatment11 = new MedicalWorkerTreatment(list5.get(5), medicalTreatmentGeneralAnesthesia2, LocalDateTime.of(2020,5,25,12,13,14), LocalDateTime.of(2020,5,25,20,13,14));

            medicalTreatmentLocalAnesthesia1.setPatient(list1.get(0));
            medicalTreatmentLocalAnesthesia2.setPatient(list1.get(0));

            medicalTreatmentLocalAnesthesia3.setPatient(list1.get(0));
            medicalTreatmentLocalAnesthesia4.setPatient(list1.get(0));
            medicalTreatmentLocalAnesthesia5.setPatient(list1.get(0));

            medicalTreatmentLocalAnesthesia6.setPatient(list1.get(4));

            medicalTreatmentGeneralAnesthesia1.setPatient(list1.get(2));
            medicalTreatmentGeneralAnesthesia2.setPatient(list1.get(0));

            session.save(medicalTreatmentLocalAnesthesia1);
            session.save(medicalTreatmentLocalAnesthesia2);
            session.save(medicalTreatmentLocalAnesthesia3);
            session.save(medicalTreatmentLocalAnesthesia4);
            session.save(medicalTreatmentLocalAnesthesia5);
            session.save(medicalTreatmentLocalAnesthesia6);

            session.save(medicalTreatmentGeneralAnesthesia1);
            session.save(medicalTreatmentGeneralAnesthesia2);

            session.save(medicalWorkerTreatment1);
            session.save(medicalWorkerTreatment2);
            session.save(medicalWorkerTreatment3);
            session.save(medicalWorkerTreatment4);
            session.save(medicalWorkerTreatment5);
            session.save(medicalWorkerTreatment6);
            session.save(medicalWorkerTreatment7);
            session.save(medicalWorkerTreatment8);
            session.save(medicalWorkerTreatment9);
            session.save(medicalWorkerTreatment10);
            session.save(medicalWorkerTreatment11);
            session.save(medicalWorkerTreatment12);

            session.save(hospital);
            session.save(department1);
            session.save(department2);
            session.save(department3);
            session.save(department4);
            session.save(department5);
            session.save(room1);
            session.save(room2);
            session.save(room3);
            session.save(room4);
            session.save(room5);
            session.save(room6);
            session.save(room7);
            session.save(room8);
            session.save(room9);
            session.save(room10);
            session.save(room11);
            session.save(room12);
            session.save(room13);
            session.save(room14);
            session.save(room15);
            session.save(room16);
            session.save(patientRoom1);
            session.save(patientRoom2);
            session.save(patientRoom3);
            session.save(patientRoom4);
            session.save(patientRoom5);
            session.save(patientRoom6);
            session.save(patientRoom7);
            session.save(patientRoom8);
            session.save(patientRoom9);
            session.save(patientRoom10);
            session.save(patientRoom11);
        }



        session.beginTransaction();
        session.getTransaction().commit();
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
        List<Hospital> hospitalList = session.createQuery("from Hospital").list();
        List<Department> departmentList = session.createQuery("from Department ").list();
        List<Room> roomList = session.createQuery("from Room ").list();

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

        System.out.println("HOSPITAL");
        for(Hospital p :hospitalList){
            System.out.println(p);
            System.out.println(p.getDepartments());
        }

        System.out.println("DEPT");
        for(Department p :departmentList){
            System.out.println(p);
            System.out.println(p.getRooms());
        }

        System.out.println("ROOM");
        for(Room p :roomList){
            System.out.println(p);
        }
        System.out.println(patientList.get(0).getPatientRooms());
        System.out.println(patientList.get(2).getPatientRooms());

        List<Document> documentList  = session.createQuery("from Document ").list();
        System.out.println("Document");
        for(Document p :documentList){
            System.out.println(p);
        }
//        System.out.println(patientList.get(0).lastPatientRooms());

    }

    public static void main(final String[] args) throws Exception {
        try (Session session = getSession()) {
            create(session);
//            print(session, "Person");
            System.out.println("--------------------");
            print(session);


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