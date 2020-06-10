import model.Address;
import model.ISurgeon;
import model.MedicalWorker;
import model.actors.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GeneratorDanych {

    static List<String> maleName = List.of("Liam","Noah","William","James","Oliver","Benjamin","Elijah","Lucas","Mason","Logan","Alexander","Ethan","Jacob","Michael","Daniel","Henry","Jackson","Sebastian","Aiden","Matthew","Samuel","David","Joseph","Carter","Owen","Wyatt","John","Jack","Luke","Jayden","Dylan","Grayson","Levi","Isaac","Gabriel","Julian","Mateo","Anthony","Jaxon","Lincoln","Joshua","Christopher","Andrew","Theodore","Caleb","Ryan","Asher","Nathan","Thomas","Leo");
    static List<String>  femaleName = List.of("Emma","Olivia","Ava","Isabella","Sophia","Charlotte","Mia","Amelia","Harper","Evelyn","Abigail","Emily","Elizabeth","Mila","Ella","Avery","Sofia","Camila","Aria","Scarlett","Victoria","Madison","Luna","Grace","Chloe","Penelope","Layla","Riley","Zoey","Nora","Lily","Eleanor","Hannah","Lillian","Addison","Aubrey","Ellie","Stella","Natalie","Zoe","Leah","Hazel","Violet","Aurora","Savannah","Audrey","Brooklyn","Bella","Claire","Skylar");
    static List<String>  malePESEL = List.of("60012375895","49112012457","74011572535","47042639199","70091812354","60123066895","53060883753","95090293894","74121276259","95051343938","84021199615","99110157175","71121453116","79060241412","68021359877","77012061475","76062748534","45053168792","02252322811","83021148254","04212866799","83032693877","94012324313","62012046418","63021237716","72011696774","89051453531","92012672436","01301075674","03222844153","85032753555","65032341479","64040659392","93122641512","66110143156","72061659657","57100166879","72113099219","52021688257","89120543833","68122171231","88031392819","53080541651","71092853117","70101123117","45060573853","63111188496","59090823538","83120124951","47080285433");
    static List<String>  femalePESEL = List.of("57030415522","83110243884","63102382522","96061688446","96091918586","89042978443","70072741222","02211568926","57032443886","67072887722","47112619643","97030362943","84031518787","73101712864","62051251682","02232647248","88092868786","92112467246","97032357684","86100428429","87112594962","63010819325","04311928745","92010432142","99061577286","03300194862","03231974465","81091495823","78050992945","89110156182","65080579989","59111347261","70120423722","58041359382","57041291269","65063083269","47112546888","60082049469","45112621347","59040789844","61010294281","65110524183","48060877422","94060474527","02262996161","47111321686","64060649469","49122323828","89081999186","91121268484");
    static List<String>  surnames = List.of("Smith","Johnson","Williams","Jones","Brown","Davis","Miller","Wilson","Moore","Taylor","Anderson","Thomas","Jackson","White","Harris","Martin","Thompson","Garcia","Martinez","Robinson","Clark","Rodriguez","Lewis","Lee","Walker","Hall","Allen","Young","Hernandez","King","Wright","Lopez","Hill","Scott","Green","Adams","Baker","Gonzalez","Nelson","Carter","Mitchell","Perez","Roberts","Turner","Phillips","Campbell","Parker","Evans","Edwards","Collins");
    static List<String>  streets = List.of("Abecadło","Achillesa","Babicka","Babie Lato","Babiego Lata","Babimojska","Babinicza","Cegielniana","Ceglana","Cegłowska","Celestynowska","Celna","Celofanowa","Celtów","Grabiny","Grabowa","Grabowska","Grafitowa","Grajewska","Graniczna","Graniczna","Granitowa","Hipotezy","Historyczna","Marka Hłaski","Hodowlana","Holenderska","Krowia","Króla Artura","Króla Maciusia","Królewska","Królowej Aldony","Odblask","Odeska","Odkryta","Odległa","Odlewnicza","Odłogi","Odmienna","Odolańska","Odpoczynek","Olchy","Olchy","Olczyska","Oleandrów","Olecka","Oleńki","Olesin","Olesińska","Olgierda","Olimpijska","Oliwkowa","Tristana","Trocinowa","Trocka","Trojaka","Trojanowska","Trojańska","Trombity","Trójpolowa");

    public static String phone(){
        Random random = new Random();
        StringBuilder p= new StringBuilder();
        p.append(random.nextInt(9)+1);
        for(int i=0;i<8;i++){
            p.append(random.nextInt(10));
        }
        return p.toString();
    }

    public static int randomizer(int b){
        Random random = new Random();
        return random.nextInt(b);
    }

    public static String mail( String name,String surname,int n){
        String tmpName = name.toLowerCase();
        String tmpSurname = surname.toLowerCase();
        String mail = tmpName+"."+tmpSurname+"@";
        List<String> ends = List.of("gmail.com","nfz.pl","op.pl","wp.pl","onet.pl","pjwstk.edu.pl");
        return mail+ends.get(n);
    }

    public static String codePostal() {
        Random random = new Random();
        return "0" + random.nextInt(10) + "-" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
    }

    public static Address generatorA(){
        if(randomizer(2)==1){
            return new Address("Warsaw",codePostal(),"ul. "+streets.get(randomizer(streets.size())),randomizer(69));
        }else{
            return new Address("Warsaw",codePostal(),"ul. "+streets.get(randomizer(streets.size())),randomizer(69),randomizer(58));
        }
    }


    //        b=true ->male b=false -> female
    public static Patient generatorPatient(boolean b) throws Exception {
        long minDay = LocalDate.of(2010, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2019, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        if(b){
            String name=maleName.get(randomizer(50));
            String surname=surnames.get(randomizer(50));
            String mail = mail(name,surname,0);
            String phone = phone();
            String PESEL = malePESEL.get(randomizer(50));

            return new Patient(name,surname,PESEL,generatorA(),phone,mail,randomDate);
        }else{
            String name=femaleName.get(randomizer(50));
            String surname=surnames.get(randomizer(50));
            String mail = mail(name,surname,1);
            String phone = phone();
            String PESEL = femalePESEL.get(randomizer(50));

            return new Patient(name,surname,PESEL,generatorA(),phone,mail,randomDate);
        }
    }

    public static Receptionist generatorReceptionist(boolean b) throws Exception {
        String name;
        String surname;
        String mail;
        String phone;
        String PESEL;
        String login;
        String password;
        Double salary;
        String education;
        List<String> languages;
        if(b){
            name=maleName.get(randomizer(maleName.size()));
            PESEL = malePESEL.get(randomizer(malePESEL.size()));
            languages=List.of("polski","niemiecki","angieslki");
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(randomizer(femaleName.size()));
            languages=List.of("polski","niemiecki","angieslki","francuski");
        }
        surname=surnames.get(randomizer(surnames.size()));
        mail = mail(name,surname,1);
        phone = phone();
        login=name.substring(0,1)+surname;
        password="Receptionist1@";
        salary=(double)(randomizer(1500)+1500);
        education="";

        return new Receptionist(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,languages);
    }
    public static Nurse generatorNurse(boolean b) throws Exception {
        String name;
        String surname;
        String mail;
        String phone;
        String PESEL;
        String login;
        String password;
        Double salary;
        String education;
        List<String> certificates;
        MedicalWorker.Degree degree;
        if(b){
            name=maleName.get(randomizer(maleName.size()));
            PESEL = malePESEL.get(randomizer(malePESEL.size()));
            certificates=List.of();
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(randomizer(femaleName.size()));
            certificates=List.of();
        }
        surname=surnames.get(randomizer(surnames.size()));
        mail = mail(name,surname,1);
        phone = phone();
        login=name.substring(0,1)+surname;
        password="Nurse2@";
        salary=(double)(randomizer(1500)+2000);
        education="";
        degree = MedicalWorker.Degree.MASTER_DEGREE;
        return new Nurse(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,degree,certificates);
    }

    public static Doctor generatorDoctor(boolean b) throws Exception {
        String name;
        String surname;
        String mail;
        String phone;
        String PESEL;
        String login;
        String password;
        Double salary;
        String education;
        MedicalWorker.Degree degree;
        String specialization;
        if(b){

            name=maleName.get(randomizer(maleName.size()));
            PESEL = malePESEL.get(randomizer(malePESEL.size()));
            specialization = "";
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(randomizer(femaleName.size()));
            specialization = "";
        }
        surname=surnames.get(randomizer(surnames.size()));
        mail = mail(name,surname,1);
        phone = phone();
        login=name.substring(0,1)+surname;
        password="Doctor3#";
        salary=(double)(randomizer(1500)+5000);
        education="";
        degree = MedicalWorker.Degree.DOCTOR_DEGREE;
        return new Doctor(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,degree,specialization);
    }
    public static Surgeon generatorSurgeon(boolean b) throws Exception {
        String name;
        String surname;
        String mail;
        String phone;
        String PESEL;
        String login;
        String password;
        Double salary;
        String education;
        MedicalWorker.Degree degree;
        ISurgeon.Type type;
        if(b){

            name=maleName.get(randomizer(maleName.size()));
            PESEL = malePESEL.get(randomizer(malePESEL.size()));
            type = ISurgeon.Type.CARDIO;
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(randomizer(femaleName.size()));
            type = ISurgeon.Type.NEURO;
        }
        surname=surnames.get(randomizer(surnames.size()));
        mail = mail(name,surname,1);
        phone = phone();
        login=name.substring(0,1)+surname;
        password="Surgeon4$";
        salary=(double)(randomizer(1500)+10000);
        education="";
        degree = MedicalWorker.Degree.DOCTOR_DEGREE;
        return new Surgeon(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,degree,type);
    }
    public static HospitalAdministrator generatorAdmin(boolean b) throws Exception {
        String name;
        String surname;
        String mail;
        String phone;
        String PESEL;
        String login;
        String password;
        Double salary;
        String education;
        MedicalWorker.Degree degree;
        String specialization;
        ISurgeon.Type type;
        if(b){
            name=maleName.get(randomizer(maleName.size()));
            PESEL = malePESEL.get(randomizer(malePESEL.size()));
            specialization = "";
            type = ISurgeon.Type.NEURO;
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(randomizer(femaleName.size()));
            specialization = "";
            type = ISurgeon.Type.CARDIO;
        }
        surname=surnames.get(randomizer(surnames.size()));
        mail = mail(name,surname,1);
        phone = phone();
        login=name.substring(0,1)+surname;
        password="Admin3#";
        salary= (double) (randomizer(1500) + 15000);
        education="";
        degree = MedicalWorker.Degree.PROFESSOR_DEGREE ;
        return new HospitalAdministrator(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,degree,specialization,LocalDate.now(),type);

    }


    public static void main(String[] args) throws Exception {
        System.out.println(generatorPatient(true));
        System.out.println(generatorPatient(false));

        System.out.println(generatorReceptionist(true));
        System.out.println(generatorReceptionist(false));

        System.out.println(generatorNurse(true));
        System.out.println(generatorNurse(false));

        System.out.println(generatorDoctor(true));
        System.out.println(generatorDoctor(false));

        System.out.println(generatorSurgeon(true));
        System.out.println(generatorSurgeon(false));

        System.out.println(generatorAdmin(true));
        System.out.println(generatorAdmin(false));
    }
}
