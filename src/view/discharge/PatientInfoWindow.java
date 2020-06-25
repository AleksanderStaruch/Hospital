package view.discharge;

import model.actors.Doctor;
import model.actors.Patient;
import org.hibernate.Session;
import viewElements.StepBarPanel;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class PatientInfoWindow extends JPanel {

    Patient patient;
    JLabel name, surname, dateOfBirth, age, sex, info, tittle, tittleList;
    JTextField nameField, surnameField, dateOfBirthField, ageField, sexField;
    JPanel namePanel, surnamePanel, dateOfBirthPanel, agePanel, sexPanel;
    JList<Object> treatmentList;
    JPanel dataPanel, buttonPanel, infoPanel, patientPanel;
    JButton submit, cancel;

    public PatientInfoWindow(JFrame frame, Patient patient, Doctor doctor, Session session) {
        this.patient = patient;
        infoPanel = new JPanel(new BorderLayout());
        tittle = new JLabel("Discharge window:");infoPanel.add(tittle,BorderLayout.PAGE_START);
        tittle.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittle.setFont(new Font("Serif", Font.PLAIN, 25));

//        STATUS BAR
        StepBarPanel stepBarPanel = new StepBarPanel();
        stepBarPanel.step2(frame, patient, doctor, session);

        infoPanel.add(stepBarPanel,BorderLayout.PAGE_END);


        name = new JLabel("Name: ", SwingConstants.RIGHT);
        surname = new JLabel("Surname: ", SwingConstants.RIGHT);
        dateOfBirth = new JLabel("Date of birth: ", SwingConstants.RIGHT);
        age = new JLabel("Age: ", SwingConstants.RIGHT);
        sex = new JLabel("Sex: ", SwingConstants.RIGHT);

        nameField = new JTextField(patient.getName(),20);nameField.setEditable(false);
        surnameField = new JTextField(patient.getSurname(),20);surnameField.setEditable(false);
        dateOfBirthField = new JTextField(patient.getBirthDate().toString(),20);dateOfBirthField.setEditable(false);
        ageField = new JTextField(patient.getAge()+"",20);ageField.setEditable(false);
        sexField = new JTextField(patient.getSex()+"",20);sexField.setEditable(false);

        namePanel = new JPanel(new GridLayout(1,2,5,5));
        surnamePanel = new JPanel(new GridLayout(1,2,5,5));
        dateOfBirthPanel = new JPanel(new GridLayout(1,2,5,5));
        agePanel = new JPanel(new GridLayout(1,2,5,5));
        sexPanel = new JPanel(new GridLayout(1,2,5,5));

        namePanel.add(name);namePanel.add(nameField);
        surnamePanel.add(surname);surnamePanel.add(surnameField);
        dateOfBirthPanel.add(dateOfBirth);dateOfBirthPanel.add(dateOfBirthField);
        agePanel.add(age);agePanel.add(ageField);
        sexPanel.add(sex);sexPanel.add(sexField);

        patientPanel = new JPanel();
        patientPanel.setLayout(new GridLayout(15,1,5,5));
        patientPanel.add(new JPanel());
        patientPanel.add(namePanel);
        patientPanel.add(surnamePanel);
        patientPanel.add(dateOfBirthPanel);
        patientPanel.add(agePanel);
        patientPanel.add(sexPanel);

        var from = patient.lastPatientRooms().getFromm();
        var treatments = patient.getMedicalTreatments().stream().filter(p->p.getMedicalWorkerTreatments().get(0).getFromm().toLocalDate().isAfter(from)).collect(Collectors.toList());
        treatmentList = new JList<>(treatments.toArray());
        treatmentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        treatmentList.setLayoutOrientation(JList.VERTICAL_WRAP);

//        render

        JScrollPane scroller = new JScrollPane(treatmentList);
//        scroller.setPreferredSize(new Dimension(750, 80));

        tittleList = new JLabel("Treatment list:");
        tittleList.setFont(new Font("Serif", Font.PLAIN, 20));
        tittleList.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittleList.setForeground(Color.BLUE);

        dataPanel = new JPanel(new BorderLayout(10,10));
        dataPanel.add(tittleList,BorderLayout.PAGE_START);
        dataPanel.add(scroller,BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        submit = new JButton("SUBMIT");buttonPanel.add(submit);
        frame.getRootPane().setDefaultButton(submit);
        cancel = new JButton("CANCEL");buttonPanel.add(cancel);

        submit.addActionListener(l->{
            TextWindow window = new TextWindow(frame,patient,doctor, session);
            frame.setContentPane(window);
            SwingUtilities.updateComponentTreeUI(frame);
        });

        cancel.addActionListener(l->{
            frame.setContentPane(new JPanel());
            SwingUtilities.updateComponentTreeUI(frame);
        });

        this.setLayout(new BorderLayout(10,10));
        this.setBorder(BorderFactory.createEmptyBorder(5, 25, 10, 25));
        this.add(infoPanel, BorderLayout.PAGE_START);
        this.add(patientPanel, BorderLayout.LINE_START);
        this.add(dataPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
