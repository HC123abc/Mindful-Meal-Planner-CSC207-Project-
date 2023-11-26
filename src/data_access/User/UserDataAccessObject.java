package data_access.User;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import entity.FavouriteRecipes;
import entity.Preference;
import entity.Recipe;
import use_case.signUp.SignUpDataAccessInterface;
import app.userFactory;
import entity.User;
import use_case.login.LoginDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserDataAccessObject implements SignUpDataAccessInterface, LoginDataAccessInterface {

    private User currentUser = null; // current user
    private String txt = "userFile.txt";

    public void txtChange(String fileName){
        this.txt = fileName;
    }

    @Override
    public User createUser(String username, String password){
        userFactory factory = new userFactory();
        User user = factory.createUser(username, password);
        return user;
    }

    @Override
    public String storeUser(User user, String password, String PasswordCheck) {
        if (!user.verifyPassword(PasswordCheck)) {
            return "Password not same";
        }
        if (user.getUsername().equals("")){
            return "Empty";
        }
        if (password.length() < 5){
            return "Your password is too small. 🦑";
        }
        // Check if the username is already taken in userFile.txt
        try (BufferedReader myReader = new BufferedReader(new FileReader(txt))) {
            String row;
            while ((row = myReader.readLine()) != null) {
                if (user.getUsername().equals(row)) {
                    return "Username already taken";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Unknown error occurred - Data File";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(txt, true))){
            FileWriter jsonFileWriter = new FileWriter("./users/" + user.getUsername() + ".json", true);
            textFileWriter.write(user.getUsername());
            textFileWriter.newLine();
//            FavouriteRecipes fave = new FavouriteRecipes();
//            fave.setOneFavouriteRecipe(new Recipe("", "", "", "", "", "", "", ""));
//            fave.setOneFavouriteRecipe(new Recipe("1", "1", "1", "1", "1", "1", "1", "1"));
//            user.setFavouriteRecipes(fave);
            // Write the user object to the JSON file
            Gson gson = new GsonBuilder().create();
            gson.toJson(user, jsonFileWriter);
            jsonFileWriter.flush();
            jsonFileWriter.close();

            return "Success!";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUser(String user, String password) {
        try (BufferedReader myReader = new BufferedReader(new FileReader(txt))) {
            String row;
            while ((row = myReader.readLine()) != null) {
                if (user.equals(row)) {
                    return userCheck(user, password);
                }
            }
            System.out.println("No user");
            return "User does not exist error.";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String userCheck(String user, String password){
        Gson gson = new GsonBuilder().create();
        try (JsonReader reader = new JsonReader(new FileReader("./users/"+user+".json"))) {
            // Convert JSON File to Java Object
            Path path = Paths.get("./users/"+user+".json");
            String jsonString =
                    new String(Files.readAllBytes(path));
            JsonObject outerObject = new Gson().fromJson(reader, JsonObject.class);
            JsonObject prefs = outerObject.getAsJsonObject("preference");
            JsonObject faves = outerObject.getAsJsonObject("favouriteRecipes");
            ArrayList<Recipe> favourites = new ArrayList<>();
            FavouriteRecipes fave = gson.fromJson(faves, FavouriteRecipes.class);
            System.out.println(fave);
            if (faves != null) {
                JsonArray favouritesJson = faves.getAsJsonArray("favouriteRecipes");
                for (JsonElement i : favouritesJson) {
                    favourites.add(gson.fromJson(i, Recipe.class));
                }
                for (Recipe i : favourites) {
                    fave.setOneFavouriteRecipe(i);
                    System.out.println(i);
                }
            }
            Preference pref = gson.fromJson(prefs, Preference.class);
            User userCheck = new User(gson.fromJson(outerObject.get("username"), String.class),
                    gson.fromJson(outerObject.get("password"), String.class));
            if (fave.getFavouriteRecipes() == null){
                fave.replaceRecipes(new ArrayList<>());
            }
            userCheck.setFavouriteRecipes(fave);
            userCheck.setPreference(pref);
            System.out.println(userCheck.getPreference());
            System.out.println(userCheck.getFavouriteRecipes());
            if (userCheck.verifyPassword(password)) {
                this.currentUser = userCheck;
                System.out.println(this.currentUser.getPreference().getSelectedCuisines());
                return "Success!";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Passwords are not matching.";
    }
    @Override
    public User setUser(){
        return currentUser;
    }
}
