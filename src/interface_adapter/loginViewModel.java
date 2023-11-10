package interface_adapter;

import java.beans.PropertyChangeListener;

public class loginViewModel extends ViewModel {

    public loginViewModel() {
        super("login");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
    public loginState getState(){ return new loginState(); }

    public void setState(loginState loginState) {
    }
}
