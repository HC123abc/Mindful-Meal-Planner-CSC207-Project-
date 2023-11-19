package interface_adapter.signUp;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class signUpViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String CHECK_PASSWORD_LABEL = "Enter password again";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public String viewName = "Sign Up";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private signUpState state = new signUpState();
    public signUpViewModel() {
        super("Sign Up");
    }

    public signUpState getState() {
        return this.state;
    }

    public void setState(signUpState state) {
        this.state = state;
    }


    @Override
    public void firePropertyChanged() {
        System.out.println(this.state.getError()+"error");
        System.out.println(this.state.getEmpty()+"empty");
        support.firePropertyChange("SignUpStateChanged", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


}
