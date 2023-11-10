package use_case.signUp;
import entity.User;

public class signUpOutputData {
    private User user;
    public signUpOutputData(User user){
        this.user = user;
    }

    public String getUsername(){ return this.user.getUsername(); }
}
