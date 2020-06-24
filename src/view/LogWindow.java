package view;

import model.Worker;
import org.hibernate.Session;
import viewElements.WorkerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LogWindow extends JPanel {
    JFrame frame;

    JLabel login_label, password_label, message, tittle;
    JTextField login_text;
    JPasswordField password_text;
    JButton submit, cancel;
    JPanel dataPanel, panel;

    Session session;
    public LogWindow(JFrame frame, Session session) {
        this.frame = frame;
        this.session = session;

        // Login
        login_label = new JLabel("Login :");
        login_text = new JTextField(20);
        // Password
        password_label = new JLabel("Password :");
        password_text = new JPasswordField(20);
        // Submit, cancel, message for errors
        message = new JLabel();
        submit = new JButton("SUBMIT");
        frame.getRootPane().setDefaultButton(submit);
        cancel = new JButton("CANCEL");

        dataPanel = new JPanel(new GridLayout(5, 2,5,5));
        dataPanel.add(login_label);dataPanel.add(login_text);
        dataPanel.add(password_label);dataPanel.add(password_text);
        dataPanel.add(submit);dataPanel.add(cancel);
        dataPanel.add(message);

        submit.addActionListener(this::actionPerformed);
        cancel.addActionListener(l->{ System.exit(0); });

        tittle = new JLabel("Login window:");
        tittle.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittle.setFont(new Font("Serif", Font.PLAIN, 25));

        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(tittle);
        panel.add(dataPanel);

        this.add(panel,BorderLayout.CENTER);
    }


    private void actionPerformed(ActionEvent l) {
        System.out.println(login_text.getText() + " " + String.valueOf(password_text.getPassword()));
        List<Worker> list = session.createQuery("from Worker").list();
        Worker woker = null;
        boolean loginSuccessful = false;
        for (Worker person : list) {
            if (person.getLogin().equals(login_text.getText()) && person.getPassword().equals(String.valueOf(password_text.getPassword()))) {
                loginSuccessful = true;
                woker = person;
                break;
            }
        }
        if (loginSuccessful) {
            message.setText("Login successful.");
            message.setForeground(Color.GREEN);
            String worekrType = woker.getClass().toString().split("\\.")[2];
            switch(worekrType){
                case "Nurse" -> frame.setContentPane(new NurseWindow(frame,woker,session));
                case "Receptionist" -> frame.setContentPane(new ReceptionistWindow(frame,woker,session));
                case "Doctor" -> frame.setContentPane(new DoctorWindow(frame,woker,session));
                case "Surgeon" -> frame.setContentPane(new SurgeonWindow(frame,woker,session));
                case "HospitalAdministrator" -> frame.setContentPane(new HospitalAdministratorWindow(frame,woker,session));
            }
            SwingUtilities.updateComponentTreeUI(frame);
        } else {
            message.setText("Wrong password or login!");
            message.setForeground(Color.RED);
        }
    }
}
