package view;

import interface_adapter.CookThis.CookThisState;
import interface_adapter.CookThis.CookThisViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.Map;

public class CookThisView extends JPanel implements PropertyChangeListener {
    public String viewName = "CookThis";
    private JPanel ingredientsPanel;
    private JTextArea instructionsTextArea;
    private CookThisViewModel cookThisViewModel;

    public CookThisView(CookThisViewModel cookThisViewModel, ViewManagerModel viewManagerModel) {
        this.cookThisViewModel = cookThisViewModel;
        this.cookThisViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        JLabel ingredientsTitleLabel = new JLabel("Ingredients");
        JLabel instructionsTitleLabel = new JLabel("Instructions");

        ingredientsPanel = new JPanel(new GridBagLayout());

        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
        ingredientsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ingredientsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(ingredientsTitleLabel, BorderLayout.NORTH);
        add(ingredientsScrollPane, BorderLayout.WEST);

        instructionsTextArea = new JTextArea(10, 40);
        instructionsTextArea.setEditable(false);
        JScrollPane instructionsScrollPane = new JScrollPane(instructionsTextArea);
        add(instructionsTitleLabel, BorderLayout.CENTER);
        add(instructionsScrollPane, BorderLayout.CENTER);

        updateView(this.cookThisViewModel.getState());
    }

    public void updateView(CookThisState state) {
        ingredientsPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        Map<String, String> ingredientsWithImage = state.getIngredientsWithImage();

        for (Map.Entry<String, String> entry : ingredientsWithImage.entrySet()) {
            String ingredient = entry.getKey();
            String imageURL = entry.getValue();

            JPanel ingredientPanel = new JPanel(new BorderLayout());

            JLabel imageLabel = new JLabel();
            try {
                ImageIcon imageIcon = new ImageIcon(new URL(imageURL));
                Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                imageLabel.setIcon(imageIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }

            JLabel ingredientLabel = new JLabel("<html>" + ingredient + "</html>");
            ingredientLabel.setPreferredSize(new Dimension(150, 50));
            ingredientLabel.setVerticalAlignment(SwingConstants.TOP);
            ingredientLabel.setVerticalTextPosition(SwingConstants.TOP);
            ingredientLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            ingredientLabel.setOpaque(true);
            ingredientLabel.setBackground(Color.WHITE);
            ingredientLabel.setHorizontalAlignment(SwingConstants.LEFT);
            ingredientLabel.setToolTipText(ingredient); // Add tooltip for full text view on hover

            ingredientPanel.add(imageLabel, BorderLayout.WEST);
            ingredientPanel.add(ingredientLabel, BorderLayout.CENTER);

            ingredientsPanel.add(ingredientPanel, gbc);
            gbc.gridy++;
        }

        instructionsTextArea.setText(state.getInstruction());
        instructionsTextArea.setLineWrap(true);
        instructionsTextArea.setWrapStyleWord(true);

        revalidate();
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof CookThisState) {
            CookThisState state = (CookThisState) evt.getNewValue();
            updateView(state);
        }
    }

    public static void main(String[] args) {
        // Example usage:

    }
}
