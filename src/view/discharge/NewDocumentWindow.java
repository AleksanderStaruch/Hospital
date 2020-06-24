package view.discharge;

import model.Document;
import model.actors.Doctor;
import model.actors.Patient;
import org.hibernate.Session;
import view.DoctorWindow;
import view.MainWindow;

import javax.swing.*;
import java.awt.*;

public class NewDocumentWindow extends JPanel {
//    last stay setTo to LocalDateTime
    JLabel tittle, doctorName, doctorSurname, patientName, patientSurname, date, city;
    JTextField doctorNameFiled, doctorSurnameFiled, patientNameFiled, patientSurnameFiled, dateFiled, cityFiled;
    JPanel doctorNamePanel, doctorSurnamePanel, patientNamePanel, patientSurnamePanel, datePanel, cityPanel;

    JPanel doctorPanel,patientPanel, dateCityPanel;
    JButton submit, print;
    JPanel dataPanel,buttonPanel,infoPanel, mainPanel;
    JTextArea description;

    public NewDocumentWindow(JFrame frame, Document document, Session session) {
        infoPanel = new JPanel(new BorderLayout());
        tittle = new JLabel("Discharge window:");infoPanel.add(tittle,BorderLayout.PAGE_START);
        tittle.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        tittle.setFont(new Font("Serif", Font.PLAIN, 25));

//        STATUS BAR
        doctorName = new JLabel("Doctor name: ", SwingConstants.RIGHT);
        doctorSurname = new JLabel("Doctor surname: ", SwingConstants.RIGHT);
        patientName = new JLabel("Patient name: ", SwingConstants.RIGHT);
        patientSurname = new JLabel("Patient surname: ", SwingConstants.RIGHT);
        date = new JLabel("Date: ", SwingConstants.RIGHT);
        city = new JLabel("City: ", SwingConstants.RIGHT);

        doctorNameFiled = new JTextField(document.getDoctor().getName(),20);doctorNameFiled.setEditable(false);
        doctorSurnameFiled = new JTextField(document.getDoctor().getSurname(),20);doctorSurnameFiled.setEditable(false);
        patientNameFiled = new JTextField(document.getPatient().getName(),20);patientNameFiled.setEditable(false);
        patientSurnameFiled = new JTextField(document.getPatient().getSurname(),20);patientSurnameFiled.setEditable(false);
        dateFiled = new JTextField(document.getDate().toString(),20);dateFiled.setEditable(false);
        cityFiled = new JTextField("Warsaw",20);cityFiled.setEditable(false);

        doctorNamePanel = new JPanel();doctorNamePanel.add(doctorName);doctorNamePanel.add(doctorNameFiled);
        doctorSurnamePanel = new JPanel();doctorSurnamePanel.add(doctorSurname);doctorSurnamePanel.add(doctorSurnameFiled);
        patientNamePanel = new JPanel();patientNamePanel.add(patientName);patientNamePanel.add(patientNameFiled);
        patientSurnamePanel = new JPanel();patientSurnamePanel.add(patientSurname);patientSurnamePanel.add(patientSurnameFiled);
        datePanel = new JPanel();datePanel.add(date);datePanel.add(dateFiled);
        cityPanel = new JPanel();cityPanel.add(city);cityPanel.add(cityFiled);

        doctorPanel = new JPanel(new GridLayout(2,2));
        patientPanel = new JPanel(new GridLayout(2,2));
        dateCityPanel = new JPanel(new GridLayout(2,2));

        doctorPanel.add(doctorName);doctorPanel.add(doctorNameFiled);
        doctorPanel.add(doctorSurname);doctorPanel.add(doctorSurnameFiled);

        patientPanel.add(patientName);patientPanel.add(patientNameFiled);
        patientPanel.add(patientSurname);patientPanel.add(patientSurnameFiled);

        dateCityPanel.add(date);dateCityPanel.add(dateFiled);
        dateCityPanel.add(city);dateCityPanel.add(cityFiled);

        dataPanel = new JPanel(new GridLayout(1,3,5,5));
        dataPanel.add(patientPanel);dataPanel.add(doctorPanel);dataPanel.add(dateCityPanel);

        description = new JTextArea(document.getDescription());
        description.setEditable(false);
        JScrollPane scroller = new JScrollPane(description);
        scroller.setPreferredSize(new Dimension(750, 80));

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(dataPanel,BorderLayout.PAGE_START);
        JPanel scrollerPanel = new JPanel(new BorderLayout(20,20));scrollerPanel.add(scroller);
        mainPanel.add(scrollerPanel,BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        submit = new JButton("CLOSE");buttonPanel.add(submit);
        frame.getRootPane().setDefaultButton(submit);
        buttonPanel.add(Box.createHorizontalGlue());
        print = new JButton("PRINT");buttonPanel.add(print);

        submit.addActionListener(l->{
//            session.save(document);
//            session.beginTransaction();
//            session.getTransaction().commit();
            frame.setContentPane(new JPanel());
            SwingUtilities.updateComponentTreeUI(frame);
        });

        print.addActionListener(l->{

        });

        this.setLayout(new BorderLayout(10,10));
        this.setBorder(BorderFactory.createEmptyBorder(5, 25, 10, 25));
        this.add(infoPanel, BorderLayout.PAGE_START);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
