package data_access.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import use_case.signUp.signUpDataAccessInterface;
import app.userFactory;
import entity.User;
import use_case.login.loginDataAccessInterface;

import java.io.*;

public class userDataAccessObject implements signUpDataAccessInterface, loginDataAccessInterface {

    private User currentUser = null; // current user
    @Override
    public User createUser(String username, String password){
        userFactory factory = new userFactory();
        User user = factory.createUser(username, password);
        return user;
    }

    @Override
    public String storeUser(User user, String PasswordCheck) {
        if (!user.verifyPassword(PasswordCheck)) {
            return "Password not same";
        }
        if (user.getUsername().equals("")){
            return "Empty";
        }

        // Check if the username is already taken in userFile.txt
        try (BufferedReader myReader = new BufferedReader(new FileReader("userFile.txt"))) {
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

        try (BufferedWriter textFileWriter = new BufferedWriter(new FileWriter("userFile.txt", true))){
            FileWriter jsonFileWriter = new FileWriter("./users/" + user.getUsername() + ".json", true);
            textFileWriter.write(user.getUsername());
            textFileWriter.newLine();

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
        try (BufferedReader myReader = new BufferedReader(new FileReader("userFile.txt"))) {
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
        try (Reader reader = new FileReader("./users/"+user+".json")) {
            // Convert JSON File to Java Object
            User userCheck = gson.fromJson(reader, User.class);
            if (userCheck.verifyPassword(password)) {
                this.currentUser = userCheck;
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
