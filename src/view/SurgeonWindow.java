package view;

import model.Worker;
import viewElements.InfoDialog;
import viewElements.WorkerWindow;

import javax.swing.*;
import java.awt.*;

public class SurgeonWindow extends WorkerWindow {

    JFrame frame;
    JLabel tittle;
    JPanel panel, dataPanel;
    Worker worker;
    public SurgeonWindow(JFrame frame, Worker worker) {
        super(frame,worker);
        this.frame = frame;
        this.worker = worker;
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
        var menu = this.getMenuBar();
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
    }
}
