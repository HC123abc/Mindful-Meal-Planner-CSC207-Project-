package view;

import javax.swing.*;

/**
 * A panel containing a label and a text field.
 */
// copied from CACoding assignment
class LabelTextPanel extends JPanel {

    LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
