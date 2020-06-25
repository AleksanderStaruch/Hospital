package viewElements;

import model.Document;
import model.actors.Doctor;
import model.actors.Patient;
import org.hibernate.Session;
import view.discharge.NewDocumentWindow;
import view.discharge.PatientInfoWindow;
import view.discharge.PatientListWindow;
import view.discharge.TextWindow;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StepBarPanel extends JPanel {

    private final JButton patientListButton;
    private final JButton patientInfoButton;
    private final JButton descriptionButton;
    private final JButton summaryButton;
    public StepBarPanel() {
        patientListButton = new JButton("PATIENT LIST >>");patientListButton.setEnabled(true);
        patientInfoButton = new JButton("PATIENT INFO >>");patientInfoButton.setEnabled(false);
        descriptionButton = new JButton("DESCRIPTION >>");descriptionButton.setEnabled(false);
        summaryButton = new JButton("SUMMARY");summaryButton.setEnabled(false);

        this.setLayout(new GridLayout(1,8,5,5));
        this.add(patientListButton);
        this.add(patientInfoButton);
        this.add(descriptionButton);
        this.add(summaryButton);

    }

    public void step1(JFrame frame, List<Patient> patientList, Doctor doctor, Session session){
        patientListButton.setEnabled(true);patientListButton.setForeground(Color.BLUE);
        patientInfoButton.setEnabled(false);
        descriptionButton.setEnabled(false);
        summaryButton.setEnabled(false);

        patientListButton.addActionListener(l->{
            frame.setContentPane(new PatientListWindow(frame, patientList, doctor, session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
    }
    public void step2(JFrame frame, Patient patient, Doctor doctor, Session session){
        patientListButton.setEnabled(true);
        patientInfoButton.setEnabled(true);patientInfoButton.setForeground(Color.BLUE);
        descriptionButton.setEnabled(false);
        summaryButton.setEnabled(false);

        patientListButton.addActionListener(l->{
            var list = session.createQuery("from Patient ").list();
            frame.setContentPane(new PatientListWindow(frame, list, doctor, session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
        patientInfoButton.addActionListener(l->{
            frame.setContentPane(new PatientInfoWindow(frame,patient, doctor, session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
    }
    public void step3(JFrame frame, Patient patient, Doctor doctor, Session session){
        patientListButton.setEnabled(true);
        patientInfoButton.setEnabled(true);
        descriptionButton.setEnabled(true);descriptionButton.setForeground(Color.BLUE);
        summaryButton.setEnabled(false);

        patientListButton.addActionListener(l->{
            var list = session.createQuery("from Patient ").list();
            frame.setContentPane(new PatientListWindow(frame, list, doctor, session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
        patientInfoButton.addActionListener(l->{
            frame.setContentPane(new PatientInfoWindow(frame,patient, doctor, session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
        descriptionButton.addActionListener(l->{
            frame.setContentPane(new TextWindow(frame,patient, doctor, session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
    }
    public void step4(JFrame frame, Document document, Session session){
        patientListButton.setEnabled(true);
        patientInfoButton.setEnabled(true);
        descriptionButton.setEnabled(true);
        summaryButton.setEnabled(true);summaryButton.setForeground(Color.BLUE);

        patientListButton.addActionListener(l->{
            var list = session.createQuery("from Patient ").list();
            frame.setContentPane(new PatientListWindow(frame, list, document.getDoctor(), session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
        patientInfoButton.addActionListener(l->{
            frame.setContentPane(new PatientInfoWindow(frame,document.getPatient(), document.getDoctor(), session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
        descriptionButton.addActionListener(l->{
            frame.setContentPane(new TextWindow(frame,document.getPatient(), document.getDoctor(), session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
        summaryButton.addActionListener(l->{
            frame.setContentPane(new NewDocumentWindow(frame, document, session));
            SwingUtilities.updateComponentTreeUI(frame);
        });
    }


}
