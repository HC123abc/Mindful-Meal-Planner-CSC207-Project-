package use_case.signUp;

public interface SignUpOutputBoundary {
    void prepareSuccessView(SignUpOutputData data);
    void prepareFailView(String error);

    void prepareEmptyView(String msg);
}
