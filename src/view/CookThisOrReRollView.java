package view;

import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.ReRoll.ReRollController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

public class CookThisOrReRollView extends JFrame implements PropertyChangeListener {
    private CookThisOrReRollViewModel cookThisOrReRollViewModel;
    private ReRollController reRollController;

    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel servingsLabel;
    private JLabel readyInMinutesLabel;
    private JTextArea summaryTextArea;
    private JButton cookButton;
    private JButton reRollButton;

    public CookThisOrReRollView(CookThisOrReRollViewModel cookThisOrReRollViewModel,ReRollController reRollController) {
        this.cookThisOrReRollViewModel = cookThisOrReRollViewModel;
        this.cookThisOrReRollViewModel.addPropertyChangeListener(this);
        this.reRollController = reRollController;

        // Initialize components
        titleLabel = new JLabel("Title: ");
        imageLabel = new JLabel();
        servingsLabel = new JLabel("Servings: ");
        readyInMinutesLabel = new JLabel("Ready in Minutes: ");
        summaryTextArea = new JTextArea("Summary: ");
        summaryTextArea.setLineWrap(true);
        summaryTextArea.setWrapStyleWord(true);

        cookButton = new JButton("Cook This");
        reRollButton = new JButton("Reroll");

        // Set up the layout
        setLayout(new BorderLayout());

        // Create a panel for title and image with GridBagLayout
        JPanel titleImagePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        titleImagePanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        titleImagePanel.add(imageLabel, gbc);

        // Create a panel for the other components with GridLayout
        JPanel otherComponentsPanel = new JPanel(new GridLayout(5, 1));
        otherComponentsPanel.add(servingsLabel);
        otherComponentsPanel.add(readyInMinutesLabel);
        otherComponentsPanel.add(summaryTextArea);
        otherComponentsPanel.add(cookButton);
        otherComponentsPanel.add(reRollButton);

        // Add the panels to the main frame
        add(titleImagePanel, BorderLayout.NORTH);
        add(otherComponentsPanel, BorderLayout.CENTER);

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
                reRollController.execute();
            }
        });

        // Set up the frame
        setTitle("Recipe Viewer");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Update the view with initial state
        updateView(cookThisOrReRollViewModel.getState());
    }

    private void updateView(CookThisOrReRollState cookThisOrReRollState) {
        titleLabel.setText("Title: " + cookThisOrReRollState.getTitle());
        try {
            String imageUrlString = cookThisOrReRollState.getImage();

            // Check if the URL has a protocol, if not, add "http://" as a default
            if (!imageUrlString.startsWith("http://") && !imageUrlString.startsWith("https://")) {
                imageUrlString = "http://" + imageUrlString;
            }

            URL url = new URL(imageUrlString);
            ImageIcon imageIcon = new ImageIcon(url);

            // Resize the JLabel to fit the image dimension
            imageLabel.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle other IO exceptions if needed
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

