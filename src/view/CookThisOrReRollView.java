package view;

import interface_adapter.CookThis.CookThisController;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
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


    public CookThisOrReRollView(CookThisOrReRollViewModel cookThisOrReRollViewModel,
                                ReRollController reRollController,
                                CookThisController cookThisController,
                                favouriteThisController favouriteThisController,
                                ViewManagerModel viewManagerModel) {
        this.cookThisOrReRollViewModel = cookThisOrReRollViewModel;
        this.cookThisOrReRollViewModel.addPropertyChangeListener(this);
        this.reRollController = reRollController;
        this.cookThisController = cookThisController;
        this.favouriteThisController = favouriteThisController;
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

        favouriteButton = new JButton("Favourite this recipe");
//        favouriteButton.setIcon(favouriteIconOff);

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
                cookThisController.execute(currentState.getIngredients(),currentState.getInstruction());
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
                System.out.println("favBtn clicked");
                CookThisOrReRollState state = cookThisOrReRollViewModel.getState();
                System.out.println(state.getIsFavourite());
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
                System.out.println(state.getIsFavourite());
            }
        });

//        // Set up the frame for testing
//        setTitle("Recipe Viewer");
//        setSize(1000, 700);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setVisible(true);

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

        // Update favourite button icon
        CookThisOrReRollState state = cookThisOrReRollViewModel.getState();
        if (state.getIsFavourite()) {
//            favouriteButton.setIcon(favouriteIconOn);
            System.out.println("On");
            JOptionPane.showMessageDialog(null,
                    "This recipe was favourited",
                    "Recipe favourited",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
//            favouriteButton.setIcon(favouriteIconOff);
            System.out.println("Off");
            JOptionPane.showMessageDialog(null,
                    "This recipe was unfavourited",
                    "Recipe unfavourited",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        //titleImagePanel, otherComponentsPanel
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

