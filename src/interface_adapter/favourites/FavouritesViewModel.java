package interface_adapter.favourites;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FavouritesViewModel extends ViewModel {

    // Properties
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private FavouritesState favouritesState = new FavouritesState();


    // Constructor
    public FavouritesViewModel() {
        super("Favourites");
    }


    // Getters
    public FavouritesState getState() { return this.favouritesState; }


    // Setters
    public void setState(FavouritesState favouritesState) { this.favouritesState = favouritesState; }


    // Property change methods
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("FavouritesStateChanged", null, this.favouritesState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
