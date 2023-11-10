package interface_adapter.signUp;

public class signUpState {

    private String username = "";
    private String Error = null;
    private String password = "";
    private String checkPassword = "";
    public void setError(String error) {
    }
    public String getError(){
        return this.Error;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCheckPassword() {
        return this.checkPassword;
    }

    public void setUsername(String text) { this.username = text;
    }

    public void setPassword(String s) { this.password = s;
    }

    public void setCheckPassword(String s) { this.checkPassword = s;
    }
}
