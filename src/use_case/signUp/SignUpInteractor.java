package use_case.signUp;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.User;

public class SignUpInteractor implements SignUpInputBoundary {
    private SignUpDataAccessInterface DAO;
    private SignUpOutputBoundary signUpPresenter;
    private InMemoryDataAccessUserInterface IMDAU;

    public SignUpInteractor(SignUpDataAccessInterface DAO, SignUpOutputBoundary SOB, InMemoryDataAccessUserInterface IMDAU){
        this.DAO = DAO;
        this.signUpPresenter = SOB;
        this.IMDAU = IMDAU;
    }
    @Override
    public void execute(SignUpInputData s){
        User user = DAO.createUser(s.getUsername(), s.getPassword());
        String state = DAO.storeUser(user, s.getPassword(), s.getPasswordChecker());
        if (state.equals("Success!")){
            IMDAU.setActiveUser(user);
            SignUpOutputData data = new SignUpOutputData(user);
            signUpPresenter.prepareSuccessView(data);
        }
        else if (state.equals("Empty")){
            System.out.println("empty");
            signUpPresenter.prepareEmptyView("empty");
        }
        else{
            signUpPresenter.prepareFailView(state);
        }

    }

}

