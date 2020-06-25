package view;

import model.Worker;
import model.actors.Doctor;
import model.actors.Patient;
import org.hibernate.Session;
import view.discharge.PatientListWindow;
import viewElements.InfoDialog;
import viewElements.WorkerWindow;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SurgeonWindow extends WorkerWindow{

    JFrame frame;
    JLabel tittle;
    JPanel panel, dataPanel;
    Worker worker;
    public SurgeonWindow(JFrame frame, Worker worker, Session session) {
        super(frame,worker,session);
        this.frame = frame;
        this.worker = worker;

        setMenu();

        dataPanel = new JPanel();

        dataPanel.add(new JLabel(worker.toString()));

        tittle = new JLabel("Surgeon window:");
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

        });


        JMenuItem  writeDeathCertificate = new JMenuItem("Write Death Certificate");fun.add( writeDeathCertificate);
        writeDeathCertificate.addActionListener(l->{
            var infoDialog = new InfoDialog("TODO not implemented");
            infoDialog.setVisible(true);

        });


        JMenuItem writePrescription = new JMenuItem("Write prescription");fun.add(writePrescription);
        writePrescription.addActionListener(l->{
            var infoDialog = new InfoDialog("TODO not implemented");
            infoDialog.setVisible(true);

        });


        JMenuItem writeDischargeFromHospital = new JMenuItem("Write discharge from hospital");fun.add(writeDischargeFromHospital);
        writeDischargeFromHospital.addActionListener(l->{
            List<Patient> list;
            list = this.getSession().createQuery("from Patient ").list();

            PatientListWindow patientListWindow = new PatientListWindow(frame, list,(Doctor) worker,this.getSession());
            frame.setContentPane(patientListWindow);
            SwingUtilities.updateComponentTreeUI(frame);
        });
    }
}
