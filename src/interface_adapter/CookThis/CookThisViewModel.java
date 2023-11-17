package interface_adapter.CookThis;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CookThisViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private CookThisState state = new CookThisState();

    public CookThisViewModel() {
        super("CookThis");
    }
    public CookThisState getState(){
        return this.state;
    }
    public void setState(CookThisState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("CookThisOrReRollStateChanged", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
