package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CookThisOrReRollViewModel extends ViewModel{
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private CookThisOrReRollState state = new CookThisOrReRollState();

    public CookThisOrReRollViewModel() {
        super("CookThisOrReRoll");
    }
    public CookThisOrReRollState getState(){
        return this.state;
    }
    public void setState(CookThisOrReRollState state) {
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
