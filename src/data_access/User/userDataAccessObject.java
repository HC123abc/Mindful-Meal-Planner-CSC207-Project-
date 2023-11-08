package data_access.User;
import use_case.signUp.signUpDataAccessInterface;
import app.userFactory;
import entity.User;

import java.io.*;

public class userDataAccessObject implements signUpDataAccessInterface {
    @Override
    public User createUser(String username, String password){
        userFactory factory = new userFactory();
        User user = factory.createUser(username, password);
        return user;
    }

    @Override
    public String storeUser(User user) {
        try {
            File myObj = new File("userFile.txt");
            System.out.println(myObj.exists());
            BufferedReader myReader = new BufferedReader(new FileReader(myObj));
            String row;
            while ((row = myReader.readLine()) != null) {
                if (user.getUsername().equals(row)) {
                    System.out.println(row);
                    System.out.println(user.getUsername());
                    return "Username already taken";
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "Unknown error occurred - Data File";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("userFile.txt"));
            writer.write(user.getUsername());
            writer.newLine();
            writer.close();
            return "Sign Up Success!";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
