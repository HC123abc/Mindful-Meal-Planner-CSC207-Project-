package use_case.signUp;

public class signUpInputData {
    String username;
    String password;
    public signUpInputData(String username, String password){
        this.username = username;
        this.password = password;
    }
    String getUsername(){ return this.username; }
    String getPassword(){ return this.password; }

}
