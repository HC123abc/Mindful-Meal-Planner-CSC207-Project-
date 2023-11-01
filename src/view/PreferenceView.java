package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PreferenceView {
    private JFrame frame;

    public PreferenceView() {
        frame = new JFrame("Preference Selection");
        frame.setSize(400, 300);
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

        for (String cuisine : cuisines) {
            JCheckBox checkbox = new JCheckBox(cuisine);
            panel.add(checkbox);
            cuisineCheckboxes.add(checkbox);
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
                        System.out.println(checkbox.getText() + " is selected");
//                      need a View Model and using view model to set states
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        new PreferenceView();
    }
}
