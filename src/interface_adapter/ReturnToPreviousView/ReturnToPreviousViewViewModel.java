package interface_adapter.ReturnToPreviousView;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReturnToPreviousViewViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ReturnToPreviousViewState state = new ReturnToPreviousViewState();

    public ReturnToPreviousViewViewModel() {
        super("previous");
    }
    public ReturnToPreviousViewState getState(){
        return this.state;
    }
    public void setState(ReturnToPreviousViewState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("previousChanged", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
