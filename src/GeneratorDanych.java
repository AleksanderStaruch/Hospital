import model.Address;
import model.MedicalWorker;
import model.actors.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GeneratorDanych {

    public static int femalePESELid = 0;
    public static int malePESELid = 0;
    
    static List<String> maleName = List.of("Liam","Noah","William","James","Oliver","Benjamin","Elijah","Lucas","Mason","Logan","Alexander","Ethan","Jacob","Michael","Daniel","Henry","Jackson","Sebastian","Aiden","Matthew","Samuel","David","Joseph","Carter","Owen","Wyatt","John","Jack","Luke","Jayden","Dylan","Grayson","Levi","Isaac","Gabriel","Julian","Mateo","Anthony","Jaxon","Lincoln","Joshua","Christopher","Andrew","Theodore","Caleb","Ryan","Asher","Nathan","Thomas","Leo");
    static List<String>  femaleName = List.of("Emma","Olivia","Ava","Isabella","Sophia","Charlotte","Mia","Amelia","Harper","Evelyn","Abigail","Emily","Elizabeth","Mila","Ella","Avery","Sofia","Camila","Aria","Scarlett","Victoria","Madison","Luna","Grace","Chloe","Penelope","Layla","Riley","Zoey","Nora","Lily","Eleanor","Hannah","Lillian","Addison","Aubrey","Ellie","Stella","Natalie","Zoe","Leah","Hazel","Violet","Aurora","Savannah","Audrey","Brooklyn","Bella","Claire","Skylar");
    static List<String>  malePESEL = List.of("48040249193","57091747758","83030974592","49111453411","73050485853","67072991818","01241781857","79071983453","72110575217","93061255735","87111882693","01300784713","02280663438","01271948712","92070492672","61102255334","92042373293","48073092319","81062182518","56122468257","66112046532","96091025996","86092978737","71052352636","72113053297","99073133793","50071693454","75050829356","63060556533","98013164394","95020576833","87021417819","77072386954","71073147635","46081385814","00251331674","87120212258","95061569195","51091621696","63071061116","51030642872","90053053759","56060492116","92062981236","87112236675","03241751333","50031633773","93021161337","73061862739","95100117358","76102347235","69050395913","78022286553","56100395232","89073077939","76020831751","49032642976","72042852873","66092789999","01302193315","69101992858","89060323551","70090475754","73042354754","49102981251","57050316573","66032577758","04211275837","83052197977","50052728179","92051887372","71031526696","04322135572","97111243279","52011172498","94042152999","65040737237","88071692197","56010178356","45122255459","56070589774","70121924512","76041166412","97112078551","99010589591","84041314193","00232231858","85022066513","47092167714","85012892131","98071796876","50091475717","01231672512","58121586253","63071722213","66031812436","60060316758","92082269958","54011552135");
    static List<String>  femalePESEL = List.of("53010482445","96060499287","95112895389","65032466422","03291433227","54051012224","83110911769","74072836724","63072555722","85091399422","86012254921","94120987763","60111884643","49011676848","93030395886","96111577429","89081869944","68072351125","72040361889","64092823943","51092947445","92101018244","03302293743","81082585566","76032274487","64052169528","87122343161","69111628549","98090423843","60112496748","57040648666","60091449421","58103046849","70050137582","58040985243","96011947128","96110392366","90011143249","98101078961","96081146166","96121684946","64081964127","90081191324","55110438443","83091539288","63081932228","04310923468","49071151947","66122988943","89040482966","80081828481","98072134446","58032137784","64091919166","49031569164","91061395549","78112655584","85041192389","70071397868","97070797369","63101395925","47041988881","84052194964","70072085342","74071014943","61060591864","56120376543","64030727229","54032861221","52100581125","92081522562","55080291727","70071993941","00220618964","51121493769","81100523662","74091856389","49092055464","89042335385","57120539989","57022736428","56080771983","97091937489","84020695785","52011588129","86011427968","66010913143","95092452565","00320794988","89011623464","77013167323","54072229564","75121969224","82042658287","74100561268","58041623449","57080569943","00250956128","64012397167");
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

        String name;
        String surname;
        String mail;
        String phone;
        String PESEL;
        if(b){
            name=maleName.get(randomizer(50));
            surname=surnames.get(randomizer(50));
            mail = mail(name,surname,0);
            phone = phone();
            PESEL = malePESEL.get(malePESELid);
        }else{
            name=femaleName.get(randomizer(50));
            surname=surnames.get(randomizer(50));
            mail = mail(name,surname,1);
            phone = phone();
            PESEL = femalePESEL.get(femalePESELid);
        }

        Patient patient = new Patient(name,surname,PESEL,generatorA(),phone,mail,randomDate);
        if(b){
            malePESELid++;
        }else{
            femalePESELid++;
        }
        System.out.println(PESEL);
        return patient;
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
            PESEL = malePESEL.get(randomizer(malePESELid));
            languages=List.of("polski","niemiecki","angieslki");
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(femalePESELid);
            languages=List.of("polski","niemiecki","angieslki","francuski");
        }
        surname=surnames.get(randomizer(surnames.size()));
        mail = mail(name,surname,1);
        phone = phone();
        login=name.substring(0,1)+surname;
        password="Receptionist1!";
        salary=(double)(randomizer(1500)+1500);
        education="";
        Receptionist receptionist = new Receptionist(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,languages);
        if(b){
            malePESELid++;
        }else{
            femalePESELid++;
        }
        System.out.println(PESEL);
        return receptionist;
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
            PESEL = malePESEL.get(randomizer(malePESELid));
            certificates=List.of();
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(femalePESELid);
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
        
        Nurse nurse = new Nurse(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,degree,certificates);
        if(b){
            malePESELid++;
        }else{
            femalePESELid++;
        }
        System.out.println(PESEL);
        return nurse;
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
            PESEL = malePESEL.get(randomizer(malePESELid));
            specialization = "";
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(femalePESELid);
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
        Doctor doctor = new Doctor(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,degree,specialization);
        if(b){
            malePESELid++;
        }else{
            femalePESELid++;
        }
        System.out.println(PESEL);
        return doctor;
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
        String specialization;
        Surgeon.Type type;
        if(b){

            name=maleName.get(randomizer(maleName.size()));
            PESEL = malePESEL.get(randomizer(malePESELid));
            type = Surgeon.Type.CARDIO;
            specialization = "";
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(femalePESELid);
            type = Surgeon.Type.NEURO;
            specialization = "";
        }
        surname=surnames.get(randomizer(surnames.size()));
        mail = mail(name,surname,1);
        phone = phone();
        login=name.substring(0,1)+surname;
        password="Surgeon4$";
        salary=(double)(randomizer(1500)+10000);
        education="";
        degree = MedicalWorker.Degree.DOCTOR_DEGREE;

        Surgeon surgeon = new Surgeon(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,degree,specialization,type);
        if(b){
            malePESELid++;
        }else{
            femalePESELid++;
        }
        System.out.println(PESEL);
        return surgeon;
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
        Surgeon.Type type;
        if(b){
            name=maleName.get(randomizer(maleName.size()));
            PESEL = malePESEL.get(malePESELid);
            specialization = "";
            type = Surgeon.Type.NEURO;
        }else{
            name=femaleName.get(randomizer(femaleName.size()));
            PESEL = femalePESEL.get(femalePESELid);
            specialization = "";
            type = Surgeon.Type.CARDIO;
        }
        surname=surnames.get(randomizer(surnames.size()));
        mail = mail(name,surname,1);
        phone = phone();
        login=name.substring(0,1)+surname;
        password="Admin3#";
        salary= (double) (randomizer(1500) + 15000);
        education="";
        degree = MedicalWorker.Degree.PROFESSOR_DEGREE ;

        HospitalAdministrator hospitalAdministrator = new HospitalAdministrator(name,surname,PESEL,generatorA(),phone,mail,login.toLowerCase(),password,salary,education,degree,specialization,type,LocalDate.now());
        if(b){
            malePESELid++;
        }else{
            femalePESELid++;
        }
        System.out.println(PESEL);
        return hospitalAdministrator;

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
