package interface_adapter.Login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Enter Username";
    public static final String PASSWORD_LABEL = "Enter Password";
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public String viewName = "Login";
    private LoginState state = new LoginState();
    public LoginViewModel() {
        super("login");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("LoginStateChanged", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public LoginState getState(){ return this.state; }

    public void setState(LoginState LoginState) {
        this.state = LoginState;
    }


}
