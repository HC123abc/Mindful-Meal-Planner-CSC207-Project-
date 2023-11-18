package interface_adapter.Finish;

import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FinishViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private FinishState state = new FinishState();

    public FinishViewModel() {
        super("MainPage");
    }
    public FinishState getState(){
        return this.state;
    }
    public void setState(FinishState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("MainPageChanged", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
