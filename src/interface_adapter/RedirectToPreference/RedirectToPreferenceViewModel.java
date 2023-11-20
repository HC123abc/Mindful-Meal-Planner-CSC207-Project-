package interface_adapter.RedirectToPreference;

import interface_adapter.Preference.PreferenceState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class RedirectToPreferenceViewModel extends ViewModel  {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private PreferenceState state = new PreferenceState();

    public RedirectToPreferenceViewModel() {
        super("Preference");
    }
    public PreferenceState getState(){
        return this.state;
    }
    public void setState(PreferenceState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("RedirectToPreferenceStateChanged", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
