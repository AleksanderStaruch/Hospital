package view;

import model.Worker;

import javax.swing.*;
import java.awt.*;

public class DoctorWindow extends JPanel {

    JFrame frame;
    JLabel tittle;
    JPanel panel, dataPanel;
    Worker worker;
    public DoctorWindow(JFrame frame, Worker worker) {
        this.frame = frame;
        this.worker = worker;
        dataPanel = new JPanel();

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
}
