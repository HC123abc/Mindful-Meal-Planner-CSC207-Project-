package entity;

public class User {
    private final String username;
    private final String password;
    private FavoriteRecipes favoriteRecipes;
    private Preference preference;
    User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    void setPreference(Preference preference){ //this is for initial user creation
        this.preference = preference;
    }

    void addPreference(String type, String name){
        //implement me
    }

    void removePreference(String type, String name){
        //implement me
    }

    void addFavourite(Recipe recipe){
        //implement me
    }

    void removeFavourite(Recipe recipe){
        //implement me
    }

}
