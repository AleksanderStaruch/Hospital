package view.discharge;

import model.MedicalTreatment;
import model.PatientRoom;
import model.actors.Doctor;
import model.actors.Patient;
import org.hibernate.Hibernate;
import view.MainWindow;
import viewElements.InfoDialog;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
import static model.MedicalTreatment.*;


public class PatientListWindow extends JPanel {

    JLabel tittle;
    JButton submit, cancel;
    JPanel dataPanel,buttonPanel,infoPanel;
    JList<Object> list;

    private boolean succeeded;

    public PatientListWindow(JFrame frame, List<Patient> patientList, Doctor doctor) {

        infoPanel = new JPanel(new BorderLayout());
        tittle = new JLabel("Discharge window:");infoPanel.add(tittle,BorderLayout.PAGE_START);
        tittle.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittle.setFont(new Font("Serif", Font.PLAIN, 25));

//        STATUS BAR

        list = new JList<>(patientList.toArray());
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
//        list.setVisibleRowCount(20);
        list.setSelectedIndex(0);
        list.setFont(new Font("Serif",Font.BOLD,16));
        list.setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        submit = new JButton("SUBMIT");buttonPanel.add(submit);
//        buttonPanel.add(Box.createHorizontalGlue());
        cancel = new JButton("CANCEL");buttonPanel.add(cancel);

        submit.addActionListener(l->{
            List<Patient> patients;
            List<PatientRoom> patientRoomList;
            Patient patientSelected ;
            Patient patient;
            PatientRoom patientRooms;
            try(var session = MainWindow.getSession()) {
                patients = session.createQuery("from Patient ").list();
                session.createQuery("from Department ").list();
                session.createQuery("from Room ").list();
                patientRoomList = session.createQuery("from PatientRoom ").list();

                patientSelected = patientList.get(list.getSelectedIndex());
                patient = patients.stream().filter(p -> p.getId() == patientSelected.getId()).collect(Collectors.toList()).get(0);
                patientRooms = patientRoomList.stream().filter(p -> p.getPatient().getId() == patientSelected.getId()).collect(Collectors.toList()).get(0);
            }
            System.out.println(patient);
            System.out.println(patientRooms);
            System.out.println(patient.getPatientRooms());
            var from = patient.lastPatientRooms().getFromm();
            if(patient.lastPatientRooms() != null){
                var infoDialog = new InfoDialog("Patient is already discharged.");
                infoDialog.setVisible(true);
            }else{
                var treatments = patientSelected.getMedicalTreatments().stream()
                        .filter(p->p.getMedicalWorkerTreatments().get(0).getFromm().toLocalDate().isAfter(from))
                        .collect(Collectors.toList());

                boolean isHealthy = true;
                for(MedicalTreatment t:treatments){
                    int days=0;
                    switch (t.getType()){
                        case NORMAL->days = TypeDays[0];
                        case MINIMALLY_INVASIVE-> days = TypeDays[1];
                        case INVASIVE-> days = TypeDays[2];
                        case DANGEROUS-> days = TypeDays[3];
                    }
                    var date = t.getMedicalWorkerTreatments().get(0).getFromm().toLocalDate();
                    long countDays = DAYS.between(date, LocalDate.now());
                    if(days>countDays){
                        isHealthy = false;
                        break;
                    }
                }
                if(isHealthy){
                    PatientInfoWindow window = new PatientInfoWindow(frame,patientSelected, doctor);
                    frame.setContentPane(window);
                }else{
                    var infoDialog = new InfoDialog("Patient is not ready to be discharged.");
                    infoDialog.setVisible(true);
                }
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        cancel.addActionListener(l->{
            frame.setContentPane(new JPanel());
            SwingUtilities.updateComponentTreeUI(frame);
        });

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10));
        this.add(infoPanel, BorderLayout.PAGE_START);
        this.add(new JScrollPane(list), BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
