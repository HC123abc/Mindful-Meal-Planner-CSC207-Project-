package interface_adapter.ReturnToPreviousView;

public class ReturnToPreviousViewState {

    private String previous = "";


    // Constructors
    public ReturnToPreviousViewState(ReturnToPreviousViewState copy) {
        this.previous = copy.previous;

    }

    public ReturnToPreviousViewState() {
        // Default constructor
    }

    // Getters and Setters


    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}

