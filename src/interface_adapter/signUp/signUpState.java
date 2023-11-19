package interface_adapter.signUp;

public class signUpState {

    private String username = "";
    private String error = null;
    private String password = "";
    private String checkPassword = "";
    private String empty = null;
    public void setError(String error) { this.error = error;
    }
    public String getError(){
        return this.error;
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

    public void setEmpty(String msg) { this.empty = msg;
    }
    public String getEmpty(){
        return this.empty;
    }
}
