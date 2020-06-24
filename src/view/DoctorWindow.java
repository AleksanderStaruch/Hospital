package view;

import model.Worker;
import model.actors.Doctor;
import model.actors.Patient;
import view.discharge.PatientListWindow;
import viewElements.InfoDialog;
import viewElements.WorkerWindow;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DoctorWindow extends WorkerWindow {

    JFrame frame;
    JLabel tittle;
    JPanel panel, dataPanel;
    Worker worker;
    public DoctorWindow(JFrame frame, Worker worker) {
        super(frame,worker);
        this.frame = frame;
        this.worker = worker;
        dataPanel = new JPanel();
        setMenu();

        dataPanel.add(new JLabel(worker.toString()));

        tittle = new JLabel("Doctor window:");
        tittle.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittle.setFont(new Font("Serif", Font.PLAIN, 25));

        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(tittle);
        panel.add(dataPanel);

        this.add(panel,BorderLayout.CENTER);
    }

    public void setMenu(){
        var fun = this.getFunction();
        JMenuItem performTreatment = new JMenuItem("Perform treatment");fun.add(performTreatment);
        performTreatment.addActionListener(l->{
            var infoDialog = new InfoDialog("TODO not implemented");
            infoDialog.setVisible(true);

//            var session = MainWindow.getSession();
//            MedicalTreatment treatment = new MedicalTreatment() {};
//            session.save(treatment);
//            session.beginTransaction();
//            session.getTransaction().commit();
//            session.close();
        });


        JMenuItem  writeDeathCertificate = new JMenuItem("Write Death Certificate");fun.add( writeDeathCertificate);
        writeDeathCertificate.addActionListener(l->{
            var infoDialog = new InfoDialog("TODO not implemented");
            infoDialog.setVisible(true);
//            var session = MainWindow.getSession();
//
//
//
//            Document document = new Document();
//            document.setDoctor((Doctor) worker);
//            session.save(document);
//            session.beginTransaction();
//            session.getTransaction().commit();
//            session.close();
        });


        JMenuItem writePrescription = new JMenuItem("Write prescription");fun.add(writePrescription);
        writePrescription.addActionListener(l->{
            var infoDialog = new InfoDialog("TODO not implemented");
            infoDialog.setVisible(true);
//            var session = MainWindow.getSession();
//
//
//            Document document = new Document();
//            document.setDoctor((Doctor) worker);
//            session.save(document);
//            session.beginTransaction();
//            session.getTransaction().commit();
//            session.close();
        });


        JMenuItem writeDischargeFromHospital = new JMenuItem("Write discharge from hospital");fun.add(writeDischargeFromHospital);
        writeDischargeFromHospital.addActionListener(l->{
            List<Patient> list;
            try(var session = MainWindow.getSession()){
                list = session.createQuery("from Patient ").list();
            }
            PatientListWindow patientListWindow = new PatientListWindow(frame, list,(Doctor) worker);
            frame.setContentPane(patientListWindow);
            SwingUtilities.updateComponentTreeUI(frame);

        });
    }
}
