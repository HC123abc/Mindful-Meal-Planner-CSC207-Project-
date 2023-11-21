package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * A panel containing a label and a text field.
 */
// copied from CACoding assignment
class LabelTextPanel extends JPanel {

    LabelTextPanel(JLabel label, JTextField textField) {
        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);
        JPanel lbl = new JPanel();
        lbl.add(label);
        this.add(lbl);
        JPanel txtField = new JPanel();
        txtField.add(textField);
        textField.setBorder(BorderFactory.createEtchedBorder(2,Color.pink, Color.GRAY));
        textField.setBackground(Color.LIGHT_GRAY);
        this.add(txtField);
    }
}
