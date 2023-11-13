package view;

import interface_adapter.PreferenceState;
import interface_adapter.PreferenceViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PreferenceView extends JPanel implements PropertyChangeListener {
    private JFrame frame;
    private PreferenceViewModel preferenceViewModel;
//    private PreferenceController preferenceController

    public PreferenceView(PreferenceViewModel preferenceViewModel) {
        this.preferenceViewModel = preferenceViewModel;
        frame = new JFrame("Preference Selection");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(0, 2));

        // label for cuisines
        JLabel cuisineLabel = new JLabel("Cuisine:");
        panel.add(cuisineLabel);

        // labels checkboxes for  cuisine
        String[] cuisines = {"African", "Asian", "American", "British", "Cajun", "Caribbean", "Chinese",
                "Eastern European", "European", "French", "German", "Greek", "Indian", "Irish", "Italian",
                "Japanese", "Jewish", "Korean", "Latin American", "Mediterranean", "Mexican",
                "Middle Eastern", "Nordic", "Southern", "Spanish", "Thai", "Vietnamese"};

        List<JCheckBox> cuisineCheckboxes = new ArrayList<>();
//      adds the checkbox to the UI
        for (String cuisine : cuisines) {
            JCheckBox checkbox = new JCheckBox(cuisine);
            PreferenceState currentState = preferenceViewModel.getState();
//          loads the old cuisines the user checked
            checkbox.setSelected(currentState.getSelectedCuisines().get(cuisine));
            panel.add(checkbox);
            cuisineCheckboxes.add(checkbox);
        }
//      observes which cuisine the user selects so we can update the state
        for (JCheckBox checkBox: cuisineCheckboxes){
            checkBox.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
//                            if (e.getStateChange()==1){
                            System.out.println(checkBox.getText() + (e.getStateChange()== 1));
//                            }
                            PreferenceState currentState = preferenceViewModel.getState();
                            currentState.setSelectedCuisines(checkBox.getText(),  e.getStateChange()== 1);
                            preferenceViewModel.setState(currentState);
//          test to see if it updates
                            System.out.println(currentState.getSelectedCuisines());
                        }
                    }
            );

        }

        // Create a button to submit preferences
        JButton submitButton = new JButton("Submit Preferences");
        panel.add(submitButton);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process selected cuisines
                for (JCheckBox checkbox : cuisineCheckboxes) {
                    if (checkbox.isSelected()) {
//                      test
                        System.out.println(checkbox.getText() + " is selected");
//                      need a controller

                    }
                }
            }
        });
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

//  testing
    public static void main(String[] args) {
        PreferenceViewModel preferenceViewModel1 = new PreferenceViewModel();
        new PreferenceView(preferenceViewModel1);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }

}
