package app;
import entity.User;
import entity.Preference;
import entity.FavouriteRecipes;

public class userFactory {
    public userFactory(){

    }
    public User createUser(String username, String password){
        User user = new User(username, password);
        return user;
    }
}
