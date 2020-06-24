package viewElements;

import model.Worker;
import view.LogWindow;
import view.MainWindow;

import javax.swing.*;
import java.awt.*;

public class WorkerWindow extends JPanel {

    JMenuBar menuBar;
    JMenu profile, function, name;
    JMenuItem passwordChange, logout;

    JFrame frame;
    Worker worker;

    public WorkerWindow(JFrame frame, Worker worker) {
        this.frame = frame;
        this.worker = worker;
        menuBar = new JMenuBar();

        profile = new JMenu("Profile");

        passwordChange = new JMenuItem("Change password");profile.add(passwordChange);
        passwordChange.addActionListener(l->{
            NewPasswordDialog dialog = new NewPasswordDialog(frame);
            if(dialog.isSucceeded()){
                try {
                    worker.setPassword(dialog.getPassword());
                    var session = MainWindow.getSession();
                    session.update(worker);
                    session.beginTransaction();
                    session.getTransaction().commit();
                    session.close();
                } catch (Exception e) {
                    var infoDialog = new InfoDialog(e.getMessage());
                    infoDialog.getL().setForeground(Color.RED);
                    infoDialog.setVisible(true);
                    e.printStackTrace();
                }
            }
        });

        logout = new JMenuItem("Logout");profile.add(logout);
        logout.addActionListener(l->{
            frame.setJMenuBar(new JMenuBar());
            frame.setContentPane(new LogWindow(frame));
            SwingUtilities.updateComponentTreeUI(frame);
        });

        function = new JMenu("Functions");

        name = new JMenu("Logged as:"+worker.getName()+" "+worker.getSurname()+" ("+worker.getClass().toString().split("\\.")[2]+")");
        name.setEnabled(false);

        menuBar.add(profile);
        menuBar.add(function);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(name);
        frame.setJMenuBar(menuBar);
    }


    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenu getFunction() {
        return function;
    }
}
