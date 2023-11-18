package use_case.login;

public interface loginOutputBoundary {
    void prepareFailView(String uidState);

    void prepareSuccessView(String username);
}
