package view;

import interface_adapter.CookThis.CookThisState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.CookThisOrReRoll.GenerateRecipeController;
import interface_adapter.FavView.FavViewController;
import interface_adapter.FavView.FavViewState;
import interface_adapter.FavView.FavViewViewModel;
import interface_adapter.Logout.LogoutController;
import interface_adapter.RedirectToPreference.RedirectToPreferenceController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class MainPageView extends JPanel implements PropertyChangeListener {
    private GenerateRecipeController generateRecipeController;
    public String viewName = "MainPage";
    private CookThisOrReRollViewModel cookThisOrReRollViewModel;
    private RedirectToPreferenceController redirectToPreferenceController;
    private FavViewController favViewController;
    private FavViewViewModel favViewViewModel;
    private LogoutController logoutController;

    public MainPageView(GenerateRecipeController generateRecipeController,
                        CookThisOrReRollViewModel cookThisOrReRollViewModel,
                        RedirectToPreferenceController redirectToPreferenceController,
                        FavViewController favViewController, FavViewViewModel favViewViewModel, LogoutController logoutController) {
        this.generateRecipeController = generateRecipeController;
        this.redirectToPreferenceController = redirectToPreferenceController;
        this.favViewController = favViewController;
        this.cookThisOrReRollViewModel = cookThisOrReRollViewModel;
        this.cookThisOrReRollViewModel.addPropertyChangeListener(this);
        this.favViewViewModel = favViewViewModel;
        this.favViewViewModel.addPropertyChangeListener(this);
        this.logoutController = logoutController;

        //bg
        Color green = new Color(177, 214, 171);
        this.setBackground(green);

        //buttons
        this.setLayout(new GridLayout(3, 1, 10, 10));
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(green);

        //logo
        new logoLoading().logoLoadBig(this, green);

        buttonFactory fac = new buttonFactory();
        JButton generateRecipeBtn = fac.makeButton("Generate Recipe", 20);
                //ew JButton("Generate Recipe");
        JButton favouritesBtn = fac.makeButton("Favourites", 20);
        JButton preferencesBtn = fac.makeButton("Preferences", 20);
                //new JButton("Preferences");
        JButton signOutBtn = fac.makeButton("Sign Out", 20);
                //new JButton("Sign Out");

        generateRecipeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRecipeController.execute();
            }
        });

        favouritesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Favourites button click
                // Open the Favourites page or perform related actions
                favViewController.execute();
            }
        });

        preferencesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Preferences button click
                // Open the Preferences page or perform related actions
                redirectToPreferenceController.execute();
            }
        });

        signOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Sign Out button click
                // Perform sign-out actions and navigate to the login page
                logoutController.execute();
            }
        });

        panel.add(generateRecipeBtn);
        panel.add(favouritesBtn);
        panel.add(preferencesBtn);
        panel.add(signOutBtn);

        add(panel);
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof CookThisOrReRollState) {
            CookThisOrReRollState state = (CookThisOrReRollState) evt.getNewValue();
            if (state.getRecipeError() != null) {
                new errors().showError(this, state.getRecipeError(), "Error");
                //JOptionPane.showMessageDialog(this, state.getRecipeError());
//          reset
                state.setRecipeError(null);
            }
        }
        else if (evt.getNewValue() instanceof FavViewState) {
            FavViewState state = (FavViewState) evt.getNewValue();
            if (state.getError() != null) {
                new errors().showError(this, state.getError(), "Error");
                //JOptionPane.showMessageDialog(this, state.getError());
//          reset
                state.setRecipeError(null);
            }
        }
    }
}