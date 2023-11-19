package interface_adapter.Login;

public class loginState {

    private String username = "";
    private String password = "";
    private String error = null;

    public void setUsername(String user) {
        this.username = user;
    }

    public void setError(String uidState) {
        this.error = uidState;
    }

    public String getUsername() { return this.username;
    }

    public String getPassword() { return this.password;
    }

    public void setPassword(String s) {
        this.password = s;
    }

    public Object getError() {
        return this.error;
    }
}
