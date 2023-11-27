package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A panel containing a label and a text field.
 */
// copied from CACoding assignment
class LabelTextPanel extends JPanel {

    LabelTextPanel(JLabel label, JTextField textField, Color color) {
        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);
        JPanel lbl = new JPanel();
        lbl.add(label);
        this.add(lbl);
        JPanel txtField = new JPanel();
        txtField.add(textField, SwingConstants.CENTER);
        textField.setBorder(BorderFactory.createEtchedBorder(2,new Color(45, 71, 4), Color.GRAY));
        textField.setBackground(new Color(227, 237, 211));
        this.add(txtField);
        lbl.setBackground(color);
        txtField.setBackground(color);
    }

}
