package view.discharge;

import model.actors.Doctor;
import model.actors.Patient;
import view.MainWindow;

import javax.swing.*;
import java.awt.*;

public class TextWindow extends JPanel {
    JLabel tittle;
    JButton submit, cancel;
    JPanel dataPanel,buttonPanel,infoPanel;
    JTextArea description;

    public TextWindow(JFrame frame, Patient patient, Doctor doctor) {
        infoPanel = new JPanel(new BorderLayout());
        tittle = new JLabel("Discharge window:");infoPanel.add(tittle,BorderLayout.PAGE_START);
        tittle.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittle.setFont(new Font("Serif", Font.PLAIN, 25));

//        STATUS BAR


        description = new JTextArea();

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        submit = new JButton("SUBMIT");buttonPanel.add(submit);
//        buttonPanel.add(Box.createHorizontalGlue());
        cancel = new JButton("CANCEL");buttonPanel.add(cancel);

        submit.addActionListener(l->{
//            frame, patient, doctor

//            var session = MainWindow.getSession();
//            Document document = new Document();
//            document.setDoctor((Doctor) worker);
//            session.save(document);
//            session.beginTransaction();
//            session.getTransaction().commit();
        });

        cancel.addActionListener(l->{
            frame.setContentPane(new JPanel());
            SwingUtilities.updateComponentTreeUI(frame);
        });

        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.PAGE_START);
        this.add(description, BorderLayout.LINE_START);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
