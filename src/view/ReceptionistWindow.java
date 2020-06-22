package view;

import model.Worker;
import model.actors.Patient;
import org.hibernate.jdbc.Work;
import viewElements.InfoDialog;
import viewElements.WorkerWindow;

import javax.swing.*;
import java.awt.*;

public class ReceptionistWindow extends WorkerWindow {
    JFrame frame;
    Worker worker;

    JLabel tittle;
    JPanel panel, dataPanel;
    public ReceptionistWindow(JFrame frame, Worker worker) {
        super(frame,worker);
        this.frame = frame;
        this.worker = worker;
        setMenu();

        dataPanel = new JPanel();

        dataPanel.add(new JLabel(worker.toString()));

        tittle = new JLabel("Receptionist window:");
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
        JMenuItem registerPatient = new JMenuItem("Register Patient");fun.add(registerPatient);
        registerPatient.addActionListener(l->{
            var infoDialog = new InfoDialog("TODO not implemented");
            infoDialog.setVisible(true);

//            var session = MainWindow.getSession();
//            Patient patient = new Patient();
//            session.save(patient);
//            session.beginTransaction();
//            session.getTransaction().commit();
//            session.close();
        });
    }
}
