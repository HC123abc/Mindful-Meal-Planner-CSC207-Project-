package view;

import interface_adapter.CookThis.CookThisController;
import interface_adapter.FavView.FavViewState;
import interface_adapter.FavView.FavViewViewModel;
import interface_adapter.Finish.FinishController;
import interface_adapter.unfavouriteThis.UnfavouriteThisController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class FavView extends JPanel implements PropertyChangeListener {
    public String viewName = "FavView";
    private FavViewViewModel favViewViewModel;
    private FinishController finishController;
    private UnfavouriteThisController unfavouriteThisController;
    private CookThisController cookThisController;

    public FavView(FavViewViewModel favViewViewModel, FinishController finishController, CookThisController cookThisController, UnfavouriteThisController unfavouriteThisController) {
        this.favViewViewModel = favViewViewModel;
        this.finishController = finishController;
        this.cookThisController = cookThisController;
        this.unfavouriteThisController = unfavouriteThisController;
        this.favViewViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Go Back to Main Menu");
        backButton.addActionListener(e -> {
            // Perform action to go back to the main menu
            finishController.execute();
            System.out.println("Going back to the main menu...");
        });
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = createRecipeScrollPane();
        add(scrollPane, BorderLayout.CENTER);
    }

    private JScrollPane createRecipeScrollPane() {
        JPanel recipePanel = new JPanel(new GridLayout(0, 2)); // Two recipes per row

        JScrollPane scrollPane = new JScrollPane(recipePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    private void displayRecipeCards(JPanel recipePanel) {
        recipePanel.removeAll(); // Clear the current components
        FavViewState state = favViewViewModel.getState();
        Map<String, Map<String, String>> recipeCards = state.getRecipeWithCardImage();
        for (Map.Entry<String, Map<String, String>> entry : recipeCards.entrySet()) {
            String cardImageURL = entry.getKey();
            Map<String, String> recipeDetails = entry.getValue();

            RecipeCard recipeCard = new RecipeCard(recipeDetails, cardImageURL, this.cookThisController, this.unfavouriteThisController);
            recipePanel.add(recipeCard);
        }
        recipePanel.revalidate();
        recipePanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof FavViewState) {
            JScrollPane scrollPane = (JScrollPane) getComponent(1);
            JPanel recipePanel = (JPanel) scrollPane.getViewport().getView();
            displayRecipeCards(recipePanel); // Redisplay recipe cards with updated state
        }
    }

    private static class RecipeCard extends JPanel {
        private Map<String, String> recipeDetails;
        private String cardImageURL;
        private ImageIcon image;
        private CookThisController cookThisController;
        private UnfavouriteThisController unfavouriteThisContoller;

        public RecipeCard(Map<String, String> recipeDetails, String cardImageURL, CookThisController cookThisController, UnfavouriteThisController unfavouriteThisController) {
            this.recipeDetails = recipeDetails;
            this.unfavouriteThisContoller = unfavouriteThisController;
            this.cookThisController = cookThisController;

            this.cardImageURL = cardImageURL;


            setLayout(new BorderLayout());
            String title = recipeDetails.get("title");

            try {
                System.out.println(recipeDetails.get("recipeImageURL"));
                String recipeImageURL = recipeDetails.get("recipeImageURL");
                if (recipeImageURL != null && cardImageURL.equals(recipeImageURL)) {
                    title +=" \n (Recipe card not available. Image below is of the recipe)";

                }
                // Load the image from URL into a JLabel and resize
                if (!cardImageURL.startsWith("http://") && !cardImageURL.startsWith("https://")) {
                    cardImageURL = "http://" + cardImageURL;
                }
                image = new ImageIcon(new URL(cardImageURL));
                Image scaledImage = image.getImage().getScaledInstance(450, 560, Image.SCALE_SMOOTH);
                image = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(image);
                add(imageLabel, BorderLayout.CENTER);

                // Adding title above the image

                JLabel titleLabel = new JLabel(title);
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(titleLabel, BorderLayout.NORTH);

            } catch (IOException e) {
                e.printStackTrace();
                // Handle other IO exceptions if needed
            }


            JPanel buttonPanel = new JPanel(new GridLayout(0, 2)); // Two buttons in a row
            JButton removeFromFavoritesBtn = new JButton("Remove from Favorites");
            JButton cookThisBtn = new JButton("Cook This");

            removeFromFavoritesBtn.addActionListener(e -> {
                String recipeTitle = recipeDetails.get("title");
                String summary = recipeDetails.get("summary");
                String recipeImageURL = recipeDetails.get("recipeImageURL");
                String servings = recipeDetails.get("servings");
                String readyInMinutes = recipeDetails.get("readyInMinutes");
                String extendedIngredients = recipeDetails.get("extendedIngredients");
                String extendedInstructions = recipeDetails.get("extendedInstructions");
                String id = recipeDetails.get("id");

                unfavouriteThisController.execute(recipeTitle, summary, recipeImageURL, servings,
                        readyInMinutes, extendedIngredients, extendedInstructions, id);

                System.out.println("Removing from favorites: " + recipeDetails.get("title"));
            });

            cookThisBtn.addActionListener(e -> {
                String ingredients = recipeDetails.get("extendedIngredients");
                String instruction = recipeDetails.get("extendedInstructions");

                cookThisController.execute(ingredients,instruction,"FavView");
                System.out.println("Cooking this: " + recipeDetails.get("title"));
            });

            buttonPanel.add(removeFromFavoritesBtn);
            buttonPanel.add(cookThisBtn);
            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    public static void main(String[] args) {
        // Example usage:
        // FavViewViewModel favViewViewModel = ...; // Initialize your FavViewViewModel
        // FinishController finishController = ...; // Initialize your FinishController
        // FavView favView = new FavView(favViewViewModel, finishController);
        // Then you can add this favView to a JFrame or another container to display it
    }
}
