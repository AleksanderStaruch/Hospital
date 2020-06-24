package view;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    public static void main(String[] args){
        new MainWindow();
    }

    public MainWindow() {
        SwingUtilities.invokeLater(this::createGUI);
    }

    private static final SessionFactory ourSessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    protected void createGUI() {
        var session = getSession();

        JFrame jf = new JFrame();
        jf.setTitle("Hospital app");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocation(380,0);
//        jf.setResizable(false);

        LogWindow panel = new LogWindow(jf, session);

        jf.setJMenuBar(new JMenuBar());
        jf.getContentPane().add(panel);
        jf.setPreferredSize(new Dimension(980, 730));
        jf.pack();
        jf.setVisible(true);
//        jf.setResizable(false);
    }
}

