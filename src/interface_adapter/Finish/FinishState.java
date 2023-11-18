package interface_adapter.Finish;

public class FinishState {
    private boolean clicked = false;


    // Constructors
    public FinishState(FinishState copy) {
        this.clicked = copy.clicked;

    }

    public FinishState() {
        // Default constructor
    }

    // Getters and Setters

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean getClicked() {
        return clicked;
    }
}

