package view.discharge;

import model.actors.Doctor;
import model.actors.Patient;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class PatientInfoWindow extends JPanel {

    Patient patient;
    JLabel name, surname, dateOfBirth, age, sex, info, tittle;
    JTextField nameField, surnameField, dateOfBirthField, ageField, sexField;
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


        name = new JLabel("Name: ");
        surname = new JLabel("Surname: ");
        dateOfBirth = new JLabel("Date of birth: ");
        age = new JLabel("Age: ");
        sex = new JLabel("Sex: ");

        nameField = new JTextField(patient.getName(),20);nameField.setEditable(false);
        surnameField = new JTextField(patient.getSurname(),20);surnameField.setEditable(false);
        dateOfBirthField = new JTextField(patient.getBirthDate().toString(),20);dateOfBirthField.setEditable(false);
        ageField = new JTextField(patient.getAge()+"",20);ageField.setEditable(false);
        sexField = new JTextField(patient.getSex()+"",20);sexField.setEditable(false);

        patientPanel = new JPanel(new GridLayout(10,2));
        patientPanel.add(name);patientPanel.add(nameField);
        patientPanel.add(surname);patientPanel.add(surnameField);
        patientPanel.add(dateOfBirth);patientPanel.add(dateOfBirthField);
        patientPanel.add(age);patientPanel.add(ageField);
        patientPanel.add(sex);patientPanel.add(sexField);

        var from = patient.lastPatientRooms().getFromm();
        var treatments = patient.getMedicalTreatments().stream().filter(p->p.getMedicalWorkerTreatments().get(0).getFromm().toLocalDate().isAfter(from)).collect(Collectors.toList());
        treatmentList = new JList<>(treatments.toArray());
        treatmentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        treatmentList.setLayoutOrientation(JList.VERTICAL_WRAP);

//        render

        JScrollPane scroller = new JScrollPane(treatmentList);scroller.setPreferredSize(new Dimension(750, 80));

        dataPanel = new JPanel(new BorderLayout());
        dataPanel.add(patientPanel);
//        dataPanel.add(scroller);

        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
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
        this.add(dataPanel, BorderLayout.LINE_START);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
