package view;

import interface_adapter.favourites.*;
import interface_adapter.Finish.FinishController;
import view.FavouritesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FavouritesView extends JPanel implements PropertyChangeListener {

    // Properties
    private FavouritesViewModel favouritesViewModel;
    private FavouritesController favouritesController;
    private FinishController finishController;

    public String viewName = "Favourites";

    private JPanel panel;


    // Constructor
    public FavouritesView(FavouritesViewModel favouritesViewModel,
                          FavouritesController favouritesController,
                          FinishController finishController) {

        this.favouritesViewModel = favouritesViewModel;
        this.favouritesController = favouritesController;
        this.finishController = finishController;

        this.favouritesViewModel.addPropertyChangeListener((PropertyChangeListener) this); // TODO aPCL() to be implemented


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        placeComponents(panel);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel fixedPanel = new JPanel();
        fixedPanel.setLayout(new FlowLayout());

        JButton returnToMain = new JButton("Return to Main Menu");


        // Return to main functionality
        returnToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishController.execute();
            }
        });

    }


    private void placeComponents(JPanel panel) {
        // TODO
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO
    }
}
