package view;

import model.Worker;
import org.hibernate.Session;
import viewElements.WorkerWindow;

import javax.swing.*;
import java.awt.*;

public class HospitalAdministratorWindow extends WorkerWindow {

    JFrame frame;
    JLabel tittle;
    JPanel panel, dataPanel;
    Worker worker;
    public HospitalAdministratorWindow(JFrame frame, Worker worker, Session session) {
        super(frame,worker,session);
        this.frame = frame;
        this.worker = worker;
        dataPanel = new JPanel();

        dataPanel.add(new JLabel(worker.toString()));

        tittle = new JLabel("HospitalAdministrator window:");
        tittle.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittle.setFont(new Font("Serif", Font.PLAIN, 25));

        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(tittle);
        panel.add(dataPanel);

        this.add(panel,BorderLayout.CENTER);
    }
}
