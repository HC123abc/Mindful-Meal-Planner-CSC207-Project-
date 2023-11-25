package use_case.signUp;
import entity.User;

public class SignUpOutputData {
    private User user;
    public SignUpOutputData(User user){
        this.user = user;
    }

    public String getUsername(){ return this.user.getUsername(); }
}
