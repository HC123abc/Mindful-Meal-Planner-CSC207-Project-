package view;

import interface_adapter.CookThis.CookThisState;
import interface_adapter.Finish.FinishController;
import interface_adapter.Login.loginState;
import interface_adapter.Preference.PreferenceController;
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

    private PreferenceViewModel preferenceViewModel;
    private PreferenceController preferenceController;
    private FinishController finishController;
    public String viewName = "Preference";

    public PreferenceView(PreferenceViewModel preferenceViewModel, PreferenceController preferenceController, FinishController finishController) {
        this.preferenceViewModel = preferenceViewModel;
        this.preferenceController = preferenceController;
        this.finishController = finishController;
        this.preferenceViewModel.addPropertyChangeListener(this);


        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Using BoxLayout for vertical arrangement

        placeComponents(panel);

        JScrollPane scrollPane = new JScrollPane(panel); // Wrap the panel in a scroll pane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Show vertical scrollbar as needed

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        // Create a panel to hold fixed components (buttons in this case)
        JPanel fixedPanel = new JPanel();
        fixedPanel.setLayout(new FlowLayout()); // You can use any layout you prefer

        // Create buttons
        JButton submitButton = new JButton("Save Preferences");
        JButton returnMain = new JButton("Return to Main Menu");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process selected cuisines
                PreferenceState currentState = preferenceViewModel.getState();
                preferenceController.execute(currentState.getSelectedCuisines(), currentState.getSelectedDiets(), currentState.getSelectedIntolerances());
            }
        });

        panel.add(returnMain); // Corrected the panel addition here

// Add action listener to the returnMain button
        returnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process returning to the main menu
                finishController.execute();
            }
        });

        // Add buttons to the fixed panel
        fixedPanel.add(submitButton);
        fixedPanel.add(returnMain);

        // Set layout for the PreferenceView
        this.setLayout(new BorderLayout());

        // Add the scroll pane to the PreferenceView
        this.add(scrollPane, BorderLayout.CENTER);

        // Add the fixed panel containing buttons to the SOUTH of the PreferenceView
        this.add(fixedPanel, BorderLayout.SOUTH);
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
        for (JCheckBox checkBox : cuisineCheckboxes) {
            checkBox.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {

                            PreferenceState currentState = preferenceViewModel.getState();
                            currentState.setSelectedCuisines(checkBox.getText(), e.getStateChange() == 1);
                            preferenceViewModel.setState(currentState);
//          test to see if it updates
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
        for (String diet : diets) {
            JCheckBox checkbox = new JCheckBox(diet);
            PreferenceState currentState = preferenceViewModel.getState();
//          loads the old cuisines the user checked
            checkbox.setSelected(currentState.getSelectedDiets().get(diet));
            panel.add(checkbox);
            dietCheckboxes.add(checkbox);
        }
//      observes which cuisine the user selects so we can update the state
        for (JCheckBox checkBox : dietCheckboxes) {
            checkBox.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {

                            PreferenceState currentState = preferenceViewModel.getState();
                            currentState.setSelectedDiets(checkBox.getText(), e.getStateChange() == 1);
                            preferenceViewModel.setState(currentState);
//          test to see if it updates
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
        for (JCheckBox checkBox : intoleranceCheckboxes) {
            checkBox.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {

                            System.out.println(checkBox.getText() + (e.getStateChange() == 1));
                            PreferenceState currentState = preferenceViewModel.getState();
                            currentState.setSelectedIntolerances(checkBox.getText(), e.getStateChange() == 1);
                            preferenceViewModel.setState(currentState);
//          test to see if it updates
                            System.out.println(currentState.getSelectedIntolerances());
                        }
                    }
            );

        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof PreferenceState) {
            PreferenceState state = (PreferenceState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getSaved());
            state.setSaved("");
        }
    }

    //  testing
    public static void main(String[] args) {

    }


}

