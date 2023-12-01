package view;

import javax.swing.*;
import java.awt.*;

public class popups {

    void showPopup(JPanel panel, Object error, String title, int type){
        double i = Math.random();
        String image = "";
        if (i < 0.5){
            image = "./assets/sealOfSuccess.png";
        } else {
            image = "./assets/duck!.png";
        }
        ImageIcon imageIcon = new ImageIcon(image); // load the image to a imageIcon
        Image im = imageIcon.getImage(); // transform it
        Image newimg = im.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon imageIcon1 = new ImageIcon(newimg);  // transform it back
        JOptionPane.showMessageDialog(panel, error, title, type, imageIcon1);
    }
}
