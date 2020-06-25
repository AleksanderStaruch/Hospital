package view.discharge;

import model.Document;
import model.actors.Doctor;
import model.actors.Patient;
import org.hibernate.Session;
import view.MainWindow;
import viewElements.StepBarPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDate;

public class TextWindow extends JPanel {
    JLabel tittle;
    JButton submit, cancel;
    JPanel dataPanel,buttonPanel,infoPanel;
    JTextArea description;

    public TextWindow(JFrame frame, Patient patient, Doctor doctor, Session session) {
        infoPanel = new JPanel(new BorderLayout(5,5));
        tittle = new JLabel("Discharge window:");infoPanel.add(tittle,BorderLayout.PAGE_START);
        tittle.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittle.setFont(new Font("Serif", Font.PLAIN, 25));

//        STATUS BAR
        StepBarPanel stepBarPanel = new StepBarPanel();
        stepBarPanel.step3(frame, patient, doctor, session);

        infoPanel.add(stepBarPanel,BorderLayout.PAGE_END);

        description = new JTextArea();
        description.setFont(new Font("Serif", Font.PLAIN, 16));

        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        submit = new JButton("SUBMIT");buttonPanel.add(submit);
        frame.getRootPane().setDefaultButton(submit);
        cancel = new JButton("CANCEL");buttonPanel.add(cancel);

        submit.addActionListener(l->{
            Document document = new Document(LocalDate.now(),description.getText(), Document.Type.DISCHARGE);
            document.setDoctor(doctor);
            document.setPatient(patient);

            frame.setContentPane(new NewDocumentWindow(frame,document,session));
            SwingUtilities.updateComponentTreeUI(frame);
        });

        cancel.addActionListener(l->{
            frame.setContentPane(new JPanel());
            SwingUtilities.updateComponentTreeUI(frame);
        });

        this.setLayout(new BorderLayout(10,10));
        this.setBorder(BorderFactory.createEmptyBorder(5, 25, 10, 25));
        this.add(infoPanel, BorderLayout.PAGE_START);
        JScrollPane scroller = new JScrollPane(description);
        this.add(scroller, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
