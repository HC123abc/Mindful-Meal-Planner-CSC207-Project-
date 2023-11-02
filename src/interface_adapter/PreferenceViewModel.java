package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class PreferenceViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private PreferenceState state = new PreferenceState();

    public PreferenceViewModel() {
        super("preference");
    }
    public PreferenceState getState(){
        return this.state;
    }
    public void setState(PreferenceState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("PreferenceStateChanged", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
