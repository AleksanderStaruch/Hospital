package viewElements;

import javax.swing.*;

public class InfoDialog extends JDialog {
    JLabel l;
    public InfoDialog(String info) {
        l = new JLabel(info);

        this.add(l);
        this.setSize(100, 100);
        this.setVisible(true);
    }

    public JLabel getL() {
        return l;
    }
}
