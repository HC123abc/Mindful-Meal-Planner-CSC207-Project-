package interface_adapter.FavView;

import interface_adapter.Preference.PreferenceState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class FavViewViewModel extends ViewModel  {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private FavViewState state = new FavViewState();

    public FavViewViewModel() {
        super("FavView");
    }
    public FavViewState getState(){
        return this.state;
    }
    public void setState(FavViewState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("FavViewStateChanged", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
