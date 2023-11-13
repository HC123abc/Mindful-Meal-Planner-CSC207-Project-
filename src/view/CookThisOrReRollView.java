package view;

import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import java.net.URL;

public class CookThisOrReRollView extends JFrame implements PropertyChangeListener {
    private CookThisOrReRollViewModel cookThisOrReRollViewModel;

    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel servingsLabel;
    private JLabel readyInMinutesLabel;
    private JTextArea summaryTextArea;

    private JButton cookButton;
    private JButton reRollButton;

    public CookThisOrReRollView(CookThisOrReRollViewModel cookThisOrReRollViewModel) {
        cookThisOrReRollViewModel.addPropertyChangeListener(this);

        // Initialize components
        titleLabel = new JLabel("Title: ");
        imageLabel = new JLabel();
        servingsLabel = new JLabel("Servings: ");
        readyInMinutesLabel = new JLabel("Ready in Minutes: ");
        summaryTextArea = new JTextArea("Summary: ");

        cookButton = new JButton("Cook This");
        reRollButton = new JButton("ReRoll");

        // Set up the layout
        setLayout(new GridLayout(7, 1));
        add(titleLabel);
        add(imageLabel);
        add(servingsLabel);
        add(readyInMinutesLabel);
        add(summaryTextArea);
        add(cookButton);
        add(reRollButton);

        // Set up button actions
        cookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Cook This button click
                // You can add the logic to handle cooking here
            }
        });

        reRollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle ReRoll button click
                // You can add the logic to handle re-rolling here
            }
        });

        // Set up the frame
        setTitle("Recipe Viewer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        CookThisOrReRollState currentState = cookThisOrReRollViewModel.getState();

        // Update the view with initial state
        updateView(currentState);
    }

    private void updateView(CookThisOrReRollState cookThisOrReRollState) {
        titleLabel.setText("Title: " + cookThisOrReRollState.getTitle());
        try {
            URL url = new URL(cookThisOrReRollState.getImage());
            Image image = ImageIO.read(url);
            imageLabel.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        servingsLabel.setText("Servings: " + cookThisOrReRollState.getServings());
        readyInMinutesLabel.setText("Ready In Minutes: " + cookThisOrReRollState.getReadyInMinutes());
        summaryTextArea.setText("Summary: " + cookThisOrReRollState.getSummary());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CookThisOrReRollState state1 = (CookThisOrReRollState) evt.getNewValue();
        // Listen for changes in the RecipeState and update the view
        updateView(state1);
    }

    public static void main(String[] args) {

    }
}

