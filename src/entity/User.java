package entity;

public class User {
    private final String username;
    private final String password;
    private FavoriteRecipes favoriteRecipes = new FavoriteRecipes();
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

    public FavoriteRecipes getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(FavoriteRecipes favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}
