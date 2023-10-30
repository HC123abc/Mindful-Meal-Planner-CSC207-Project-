package entity;

public class User {
    private final String username;
    private final String password;
    private RandomRecipe randomRecipe;
    private FavoriteRecipes favoriteRecipes;
    private Recipe currentRandomRecipe;
    private Preference preference;
    User(String name, String password) {
        this.username = name;
        this.password = password;
    }


}
