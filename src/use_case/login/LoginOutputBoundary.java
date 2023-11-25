package use_case.login;

public interface LoginOutputBoundary {
    void prepareFailView(String uidState);

    void prepareSuccessView(String username);

    void prepapreEmptyView();
}
