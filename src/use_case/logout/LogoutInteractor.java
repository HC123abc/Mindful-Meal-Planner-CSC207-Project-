package use_case.logout;

import entity.User;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class LogoutInteractor implements LogoutInputBoundary {
    private String jsonFilePath; // write a path to save json here

    public LogoutInteractor(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @Override
    public void execute(String username, String password) {
        // we can add user validation before logging out
        // directly saving to JSON without any validation. we can add validation for security pursuse and stuff
        saveUserDataToJson(username, password);
    }

    private void saveUserDataToJson(String username, String password) {
        JSONObject userData = new JSONObject();
        userData.put("username", username);
        userData.put("password", password);

        try (FileWriter file = new FileWriter(jsonFilePath)) {
            file.write(userData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            // if error occurs
        }
    }
}
