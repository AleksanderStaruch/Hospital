package viewElements;

import javax.swing.*;

public class InfoDialog extends JDialog {
    JLabel l;
    public InfoDialog(String info) {
        l = new JLabel(info);
        this.add(l);
        this.setSize(200, 100);
        this.setLocation(380,0);
        this.setVisible(true);
    }

    public JLabel getL() {
        return l;
    }
}
