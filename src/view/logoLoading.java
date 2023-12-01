package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class logoLoading {

    public void logoLoad(JPanel panel, Color color){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("./assets/logoWName.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaledImage = myPicture.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        JPanel picture = new JPanel();
        picture.add(new JLabel(" "));
        picture.add(picLabel, BorderLayout.CENTER);
        picture.add(new JLabel(" "));
        picture.setBackground(color);
        panel.add(picture);
    }

    public void logoLoadBig(JPanel panel, Color color){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("./assets/logoWName.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaledImage = myPicture.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        JPanel picture = new JPanel();
        picture.add(new JLabel(" "));
        picture.add(picLabel, BorderLayout.CENTER);
        picture.add(new JLabel(" "));
        picture.setBackground(color);
        panel.add(picture);
    }
}
