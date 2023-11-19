package interface_adapter.Login;

import interface_adapter.Login.loginState;
import interface_adapter.ViewModel;
import interface_adapter.signUp.signUpState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class loginViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public String viewName = "Login";
    private loginState state = new loginState();
    public loginViewModel() {
        super("login");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.firePropertyChange("LoginStateChanged", null, this.state);
    }
    public loginState getState(){ return this.state; }

    public void setState(loginState LoginState) {
        this.state = LoginState;
    }


}
