package view;

import interface_adapter.Preference.PreferenceState;
import interface_adapter.Preference.PreferenceViewModel;

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
        panel.setLayout(new GridLayout(0, 1));

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

                            System.out.println(checkBox.getText() + (e.getStateChange()== 1));
                            PreferenceState currentState = preferenceViewModel.getState();
                            currentState.setSelectedCuisines(checkBox.getText(),  e.getStateChange()== 1);
                            preferenceViewModel.setState(currentState);
//          test to see if it updates
                            System.out.println(currentState.getSelectedCuisines());
                        }
                    }
            );

        }
        ((GridLayout) panel.getLayout()).setColumns(1);
        JLabel dietLabel = new JLabel("Diet:");
        panel.add(dietLabel);

        // labels checkboxes for  cuisine
        String[] diets = {"Gluten Free", "Vegetarian", "Vegan", "Pescetarian"};

        List<JCheckBox> dietCheckboxes = new ArrayList<>();
//      adds the checkbox to the UI
        for (String diet: diets) {
            JCheckBox checkbox = new JCheckBox(diet);
            PreferenceState currentState = preferenceViewModel.getState();
//          loads the old cuisines the user checked
            checkbox.setSelected(currentState.getSelectedDiets().get(diet));
            panel.add(checkbox);
            dietCheckboxes.add(checkbox);
        }
//      observes which cuisine the user selects so we can update the state
        for (JCheckBox checkBox: dietCheckboxes){
            checkBox.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {

                            System.out.println(checkBox.getText() + (e.getStateChange()== 1));
                            PreferenceState currentState = preferenceViewModel.getState();
                            currentState.setSelectedDiets(checkBox.getText(),  e.getStateChange()== 1);
                            preferenceViewModel.setState(currentState);
//          test to see if it updates
                            System.out.println(currentState.getSelectedCuisines());
                        }
                    }
            );

        }
        JLabel intolerancesLabel = new JLabel("Intolerances:");
        panel.add(intolerancesLabel);

        // labels checkboxes for  cuisine
        String[] intolerances = {"Dairy", "Egg", "Gluten", "Grain", "Peanut", "Seafood", "Sesame",
                "Shellfish", "Soy", "Sulfite", "Tree Nut", "Wheat"};

        List<JCheckBox> intoleranceCheckboxes = new ArrayList<>();
//      adds the checkbox to the UI
        for (String intolerance : intolerances) {
            JCheckBox checkbox = new JCheckBox(intolerance);
            PreferenceState currentState = preferenceViewModel.getState();
//          loads the old cuisines the user checked
            checkbox.setSelected(currentState.getSelectedIntolerances().get(intolerance));
            panel.add(checkbox);
            intoleranceCheckboxes.add(checkbox);
        }
//      observes which cuisine the user selects so we can update the state
        for (JCheckBox checkBox: intoleranceCheckboxes){
            checkBox.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {

                            System.out.println(checkBox.getText() + (e.getStateChange()== 1));
                            PreferenceState currentState = preferenceViewModel.getState();
                            currentState.setSelectedIntolerances(checkBox.getText(),  e.getStateChange()== 1);
                            preferenceViewModel.setState(currentState);
//          test to see if it updates
                            System.out.println(currentState.getSelectedIntolerances());
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
