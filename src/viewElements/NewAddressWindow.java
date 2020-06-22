package viewElements;


import javax.swing.*;
import java.awt.*;

public class NewAddressWindow extends JPanel {
//    String city, String codePostal, String street, Integer number, Integer numberOfLocal

    JButton submit, cancel;
    JLabel cityLabel, codePostalLabel, streetLabel, numberLabel, numberOfLocalLabel, label, message;
    JTextField cityField, codePostalField, streetField, numberField,numberOfLocalField;
    JFrame frame;
    public NewAddressWindow(JFrame frame) {
        this.frame = frame;

        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(7,2));

        cityLabel = new JLabel("City: ");dataPanel.add(cityLabel);
        cityField = new JTextField();dataPanel.add(cityField);

        codePostalLabel = new JLabel("Code postal: ");dataPanel.add(codePostalLabel);
        codePostalField = new JTextField();dataPanel.add(codePostalField);

        streetLabel = new JLabel("Street: ");dataPanel.add(streetLabel);
        streetField = new JTextField();dataPanel.add(streetField);

        numberLabel = new JLabel("Number: ");dataPanel.add(numberLabel);
        numberField = new JTextField();dataPanel.add(numberField);

        numberOfLocalLabel = new JLabel("Number of local: ");dataPanel.add(numberOfLocalLabel);
        numberOfLocalField = new JTextField();dataPanel.add(numberOfLocalField);

        submit = new JButton("SUBMIT");frame.getRootPane().setDefaultButton(submit);dataPanel.add(submit);
        cancel = new JButton("CANCEL");dataPanel.add(cancel);
        message = new JLabel();dataPanel.add(message);

        submit.addActionListener(l->{
            message.setText("Submit!");
        });
        cancel.addActionListener(l->{
            message.setText("Cancel!");
        });

        label = new JLabel("New address window");
        label.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        label.setFont(new Font("Serif", Font.PLAIN, 25));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(label);
        panel.add(dataPanel);

        this.add(panel,BorderLayout.CENTER);
    }
}
