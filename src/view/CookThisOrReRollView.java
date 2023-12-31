package view;

import interface_adapter.CookThis.CookThisController;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.Finish.FinishController;
import interface_adapter.ReRoll.ReRollController;
import interface_adapter.ViewManagerModel;
import interface_adapter.FavouriteThis.*;

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
    private favouriteThisController favouriteThisController;

    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel servingsLabel;
    private JLabel readyInMinutesLabel;
    private JTextArea summaryTextArea;
    private JButton cookButton;
    private JButton reRollButton;
    private JButton favouriteButton;


    //may need to add .getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
    //    private ImageIcon favouriteIconOn = new ImageIcon("assets/favouritesAssets/favourite_star_on_temp.png");
    //    private ImageIcon favouriteIconOff = new ImageIcon("assets/favouritesAssets/favourite_star_off_temp.png");

    private JButton finishButton;

    public CookThisOrReRollView(CookThisOrReRollViewModel cookThisOrReRollViewModel,
                                ReRollController reRollController,
                                CookThisController cookThisController,
                                favouriteThisController favouriteThisController,
                                FinishController finishController,
                                ViewManagerModel viewManagerModel) {

        this.cookThisOrReRollViewModel = cookThisOrReRollViewModel;
        this.cookThisOrReRollViewModel.addPropertyChangeListener(this);
        this.reRollController = reRollController;
        this.cookThisController = cookThisController;
        this.favouriteThisController = favouriteThisController;
        this.finishController = finishController;

        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
        Color green = new Color(184, 212, 184);

        this.setBackground(green);
        // Initialize components
        titleLabel = new JLabel("Title: ");
        titleLabel.setBackground(green);
        imageLabel = new JLabel();
        imageLabel.setBackground(green);
        servingsLabel = new JLabel("Servings: ");
        servingsLabel.setBackground(green);
        readyInMinutesLabel = new JLabel("Ready in Minutes: ");
        readyInMinutesLabel.setBackground(green);
        summaryTextArea = new JTextArea("Summary: ");
        summaryTextArea.setBackground(green);
        summaryTextArea.setLineWrap(true);
        summaryTextArea.setWrapStyleWord(true);

        buttonFactory fac = new buttonFactory();
        cookButton = fac.makeButton("Cook This", 15);
                //new JButton("Cook This");
        reRollButton = fac.makeButton("Reroll", 15);
                //new JButton("Reroll");
        finishButton = fac.makeButton("Go Back to Main Page", 15);
                //new JButton("Go Back to Main Page");
        favouriteButton = fac.makeButton("Add to Favourite", 15);
                //new JButton("Add to Favourite");

        favouriteButton = fac.makeButton("Favourite this recipe", 15);
                //new JButton("Favourite this recipe");

//        favouriteButton.setIcon(favouriteIconOff);

        // Set up the layout
        setLayout(new BorderLayout());

        // Create a panel for title and image with GridBagLayout
        JPanel titleImagePanel = new JPanel(new GridBagLayout());
        titleImagePanel.setBackground(green);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        titleImagePanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        titleImagePanel.add(imageLabel, gbc);

        // Create a panel for the other components with GridLayout
        JPanel otherComponentsPanel = new JPanel(new GridLayout(5, 1));
        otherComponentsPanel.setBackground(green);
        otherComponentsPanel.add(servingsLabel);
        otherComponentsPanel.add(readyInMinutesLabel);
        otherComponentsPanel.add(summaryTextArea);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.add(Box.createHorizontalGlue()); // Aligns buttons to the right

        // Add buttons to the buttonsPanel
        buttonsPanel.setBackground(new Color(92, 110, 92));
        buttonsPanel.add(cookButton);
        buttonsPanel.add(reRollButton);
        buttonsPanel.add(finishButton);
        buttonsPanel.add(favouriteButton);
        // Add the buttonsPanel to the main frame
        add(buttonsPanel, BorderLayout.SOUTH);


        otherComponentsPanel.add(favouriteButton);

        // Add the panels to the main frame
        add(titleImagePanel, BorderLayout.NORTH);
        add(otherComponentsPanel, BorderLayout.CENTER);

        // Set up button actions
        cookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Cook This button click
                CookThisOrReRollState currentState = cookThisOrReRollViewModel.getState();
                cookThisController.execute(currentState.getIngredients(), currentState.getInstruction(), viewName);
            }
        });

        reRollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reRollController.execute();
            }
        });


        favouriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CookThisOrReRollState state = cookThisOrReRollViewModel.getState();
                String title = state.getTitle();
                String summary = state.getSummary();
                String image = state.getImage();
                String servings = state.getServings();
                String readyInMinutes = state.getReadyInMinutes();
                String ingredients = state.getIngredients();
                String instruction = state.getInstruction();
                String id = state.getId();
                boolean isFavourite = state.getIsFavourite();
                favouriteThisController.execute(title, summary, image, servings,
                        readyInMinutes, ingredients, instruction, id, isFavourite);
//                System.out.println(state.getIsFavourite());
            }
        });


        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishController.execute();
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
            Image scaledImage = imageIcon.getImage().getScaledInstance( imageIcon.getImage().getWidth(null),  imageIcon.getImage().getHeight(null), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(scaledImage);
            // Resize the JLabel to fit the image dimension
            imageLabel.setIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle other IO exceptions if needed
        }
        servingsLabel.setText("Servings: " + cookThisOrReRollState.getServings());
        readyInMinutesLabel.setText("Ready In Minutes: " + cookThisOrReRollState.getReadyInMinutes());
        summaryTextArea.setText("Summary: " + cookThisOrReRollState.getSummary());

        // Update favourite button icon
        if (cookThisOrReRollState.getIsFavourite()) {
            favouriteButton.setText("Unfavorite");
        } else {
            favouriteButton.setText("Favorite");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof CookThisOrReRollState) {
            CookThisOrReRollState state1 = (CookThisOrReRollState) evt.getNewValue();
            // Listen for changes in the CookThisOrReRollState and update the view
            if (state1.getFavButtonClicked()) {
                if (state1.getIsFavourite()) {
                    new popups().showPopup(null, "Successfully favourited the recipe", "Favorited Recipe", JOptionPane.INFORMATION_MESSAGE);
                    //JOptionPane.showMessageDialog(null, "Successfully favorited the recipe", "Favorited Recipe", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    new popups().showPopup(null, "Successfully unfavourited the recipe", "Unfavorited Recipe", JOptionPane.INFORMATION_MESSAGE);
                    //JOptionPane.showMessageDialog(null, "Successfully unfavorited the recipe", "Unfavorited Recipe", JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(state1.getIsFavourite());
                state1.setFavButtonClicked(false);
            }
            updateView(state1);
        }
    }

    public static void main(String[] args) {
    }
}

