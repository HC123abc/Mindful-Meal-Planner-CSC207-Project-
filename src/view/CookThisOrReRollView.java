package view;

import interface_adapter.CookThis.CookThisController;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.Finish.FinishController;
import interface_adapter.ReRoll.ReRollController;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import java.net.URL;

public class CookThisOrReRollView extends JPanel implements PropertyChangeListener {
    public String viewName = "CookThisOrReRoll";
    private CookThisOrReRollViewModel cookThisOrReRollViewModel;
    private ReRollController reRollController;
    private CookThisController cookThisController;
    private FinishController finishController;
    private ViewManagerModel viewManagerModel;

    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel servingsLabel;
    private JLabel readyInMinutesLabel;
    private JTextArea summaryTextArea;
    private JButton cookButton;
    private JButton reRollButton;
    private JButton finishButton;
    private JButton favouriteButton;

    public CookThisOrReRollView(CookThisOrReRollViewModel cookThisOrReRollViewModel, ReRollController reRollController, CookThisController cookThisController, FinishController finishController, ViewManagerModel viewManagerModel) {
        this.cookThisOrReRollViewModel = cookThisOrReRollViewModel;
        this.cookThisOrReRollViewModel.addPropertyChangeListener(this);
        this.reRollController = reRollController;
        this.cookThisController = cookThisController;
        this.finishController = finishController;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);

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
        finishButton = new JButton("Go Back to Main Page");
        favouriteButton = new JButton("Add to Favourite");

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
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.add(Box.createHorizontalGlue()); // Aligns buttons to the right

        // Add buttons to the buttonsPanel
        buttonsPanel.add(cookButton);
        buttonsPanel.add(reRollButton);
        buttonsPanel.add(finishButton);
        buttonsPanel.add(favouriteButton);
        // Add the buttonsPanel to the main frame
        add(buttonsPanel, BorderLayout.SOUTH);


        // Add the panels to the main frame
        add(titleImagePanel, BorderLayout.NORTH);
        add(otherComponentsPanel, BorderLayout.CENTER);

        // Set up button actions
        cookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Cook This button click
                CookThisOrReRollState currentState = cookThisOrReRollViewModel.getState();
                cookThisController.execute(currentState.getIngredients(), currentState.getInstruction());
            }
        });

        reRollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reRollController.execute();
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishController.execute();
            }
        });
        favouriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        // Update the view with initial state
        updateView(this.cookThisOrReRollViewModel.getState());
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
        if (evt.getNewValue() instanceof CookThisOrReRollState) {
            CookThisOrReRollState state1 = (CookThisOrReRollState) evt.getNewValue();
            // Listen for changes in the CookThisOrReRollState and update the view
            updateView(state1);
        }
    }

    public static void main(String[] args) {
    }
}

