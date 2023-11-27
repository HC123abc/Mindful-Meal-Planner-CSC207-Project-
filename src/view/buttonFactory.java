package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class buttonFactory {
    JButton makeButton(String Label, int size){
        JButton b = new JButton("   "+Label+"   ");
        b.setFont(new Font("", Font.PLAIN, size));
        b.setBackground(new Color(195, 201, 212));
        b.setOpaque(true);
        b.setBorder(new RoundedBorder(2));
        return b;
    }

    private static class RoundedBorder implements Border {

        private int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }

}

