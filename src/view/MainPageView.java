package view;

import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.CookThisOrReRoll.GenerateRecipeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainPageView extends JPanel  implements PropertyChangeListener {
    private GenerateRecipeController generateRecipeController;
    public String viewName = "MainPage";
    private CookThisOrReRollViewModel cookThisOrReRollViewModel;

    public MainPageView(GenerateRecipeController generateRecipeController, CookThisOrReRollViewModel cookThisOrReRollViewModel) {
        this.generateRecipeController = generateRecipeController;
        this.cookThisOrReRollViewModel = cookThisOrReRollViewModel;
        this.cookThisOrReRollViewModel.addPropertyChangeListener(this);
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton generateRecipeBtn = new JButton("Generate Recipe");
        JButton favoritesBtn = new JButton("Favorites");
        JButton preferencesBtn = new JButton("Preferences");
        JButton signOutBtn = new JButton("Sign Out");

        generateRecipeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRecipeController.execute();
            }
        });

        favoritesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Favorites button click
                // Open the Favorites page or perform related actions
            }
        });

        preferencesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Preferences button click
                // Open the Preferences page or perform related actions
            }
        });

        signOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Sign Out button click
                // Perform sign-out actions and navigate to the login page or exit the application
            }
        });

        panel.add(generateRecipeBtn);
        panel.add(favoritesBtn);
        panel.add(preferencesBtn);
        panel.add(signOutBtn);

        add(panel);
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CookThisOrReRollState state = (CookThisOrReRollState) evt.getNewValue();
        if (state.getRecipeError() != null) {
            JOptionPane.showMessageDialog(this, state.getRecipeError());
//          reset
            state.setRecipeError(null);
        }
    }
}