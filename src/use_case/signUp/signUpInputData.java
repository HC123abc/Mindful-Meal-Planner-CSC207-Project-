package use_case.signUp;

public class signUpInputData {
    private String username;
    private String password;
    private String passwordChecker;
    public signUpInputData(String username, String password, String passwordChecker){
        this.username = username;
        this.password = password;
        this.passwordChecker = passwordChecker;
    }
    String getUsername(){ return this.username; }
    String getPassword(){ return this.password; }
    String getPasswordChecker(){ return this.passwordChecker; }

}
