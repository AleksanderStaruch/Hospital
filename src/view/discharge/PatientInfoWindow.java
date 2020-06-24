package view.discharge;

import model.actors.Doctor;
import model.actors.Patient;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class PatientInfoWindow extends JPanel {

    Patient patient;
    JLabel name, surname, dateOfBirth, age, sex, info, tittle;
    JList<Object> treatmentList;
    JPanel dataPanel,buttonPanel, infoPanel;
    JButton submit, cancel;

    public PatientInfoWindow(JFrame frame, Patient patient, Doctor doctor) {
        this.patient = patient;
        infoPanel = new JPanel(new BorderLayout());
        tittle = new JLabel("Discharge window:");infoPanel.add(tittle,BorderLayout.PAGE_START);
        tittle.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittle.setFont(new Font("Serif", Font.PLAIN, 25));

//        STATUS BAR

        dataPanel = new JPanel(new GridLayout(5,1));
        name = new JLabel("Name: "+patient.getName());dataPanel.add(name);
        surname = new JLabel("Surname: "+patient.getSurname());dataPanel.add(surname);
        dateOfBirth = new JLabel("Date of birth: "+patient.getBirthDate());dataPanel.add(dateOfBirth);
        age = new JLabel("Age: "+patient.getAge());dataPanel.add(age);
        sex = new JLabel("Sex: "+patient.getSex());dataPanel.add(sex);

        var from = patient.lastPatientRooms().getFromm();
        var treatments = patient.getMedicalTreatments().stream().filter(p->p.getMedicalWorkerTreatments().get(0).getFromm().toLocalDate().isAfter(from)).collect(Collectors.toList());
        treatmentList = new JList<>(treatments.toArray());
        treatmentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        treatmentList.setLayoutOrientation(JList.VERTICAL_WRAP);
        treatmentList.setVisibleRowCount(5);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        submit = new JButton("SUBMIT");buttonPanel.add(submit);
//        buttonPanel.add(Box.createHorizontalGlue());
        cancel = new JButton("CANCEL");buttonPanel.add(cancel);

        submit.addActionListener(l->{
            TextWindow window = new TextWindow(frame,patient,doctor);
            frame.setContentPane(window);
            SwingUtilities.updateComponentTreeUI(frame);
        });

        cancel.addActionListener(l->{
            frame.setContentPane(new JPanel());
            SwingUtilities.updateComponentTreeUI(frame);
        });

        this.setLayout(new BorderLayout());

        this.setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10));
        this.add(infoPanel, BorderLayout.PAGE_START);
        this.add(dataPanel, BorderLayout.LINE_START);
        JScrollPane listScroller = new JScrollPane(treatmentList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        this.add(listScroller, BorderLayout.LINE_END);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
