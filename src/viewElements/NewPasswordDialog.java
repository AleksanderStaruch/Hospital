package viewElements;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;

public class NewPasswordDialog  extends JDialog {

    private final JPasswordField newPassword;
    private final JPasswordField repeatedNewPassword;
    private boolean succeeded;

    public NewPasswordDialog(JFrame parent) {
        super(parent, "Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel lbUsername = new JLabel("New Password: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        newPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(newPassword, cs);

        JLabel lbPassword = new JLabel("Repeat new password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        repeatedNewPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(repeatedNewPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        JButton save = new JButton("Save");

        save.addActionListener(e->{
            if(Arrays.equals(repeatedNewPassword.getPassword(), newPassword.getPassword())){
                succeeded = true;
                dispose();
            }else{
                succeeded = false;
            }
        });
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e->{dispose(); });
        JPanel panel1 = new JPanel();
        panel1.add(save);
        panel1.add(cancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(panel1, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public String getPassword() {
        return new String(repeatedNewPassword.getPassword());
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
