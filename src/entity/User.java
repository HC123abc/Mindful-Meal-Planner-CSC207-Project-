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

    public boolean verifyPassword(String checkPassword) {
        System.out.println(checkPassword);
        System.out.println(this.password);
        return checkPassword.equals(this.password);
    }
    void setPreference(Preference preference){ //this is for initial user creation
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

    public void setFavouriteRecipes(FavouriteRecipes favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }
}
