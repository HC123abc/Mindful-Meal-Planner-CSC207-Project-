package view;

import javax.swing.*;
import java.awt.*;

public class errors {
    void showError(JPanel panel, Object error, String title){
        double i = Math.random();
        String image = "";
        if (i < 0.33){
            image = "./assets/duckError.png";
        } else if (i > 0.66) {
            image = "./assets/octoError.png";
        } else {
            image = "./assets/gooseError.png";
        }
        ImageIcon imageIcon = new ImageIcon(image); // load the image to a imageIcon
        Image im = imageIcon.getImage(); // transform it
        Image newimg = im.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon imageIcon1 = new ImageIcon(newimg);  // transform it back
        JOptionPane.showMessageDialog(panel, error, title, JOptionPane.NO_OPTION, imageIcon1);
    }
}
