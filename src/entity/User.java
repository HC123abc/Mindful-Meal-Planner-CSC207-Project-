package entity;

public class User {
    private final String username;
    private final String password;
    private FavouriteRecipes favouriteRecipes = new FavouriteRecipes();
    private Preference preference = new Preference();
    private RandomRecipe randomRecipe = new RandomRecipe();
    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }
    public String getUsername() { return this.username; }
//  need to add this password getter because of SignOut use case
    public String getPassword() { return this.password; }

    public boolean verifyPassword(String checkPassword) {
        return checkPassword.equals(this.password);
    }
    public void setPreference(Preference preference){ //this is for initial user creation
        this.preference = preference;
    }

    public Preference getPreference() {
        return this.preference;
    }

    public void setRandomRecipe(RandomRecipe randomRecipe) {
        this.randomRecipe = randomRecipe;
    }

    public RandomRecipe getRandomRecipe() {
        return randomRecipe;
    }

    public FavouriteRecipes getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(FavouriteRecipes fave) { this.favouriteRecipes = fave;
    }
}
