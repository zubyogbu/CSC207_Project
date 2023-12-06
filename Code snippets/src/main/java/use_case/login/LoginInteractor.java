package main.java.use_case.login;

import main.java.app.Constants;
import main.java.entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary userPresenter;
//    final UserFactory userFactory;

    //ORIGINAL PARAMETERS: (LoginUserDataAccessInterface userDataAccessObject,
    //                           LoginOutputBoundary userPresenter,
    //                           UserFactory userFactory)
    public LoginInteractor(LoginUserDataAccessInterface userDataAccessObject,
                           LoginOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            userPresenter.prepareFailView(username + Constants.ACCOUNT_DNE);
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                userPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(username);
                Constants.CURRENT_USER = user.getId();
                Constants.CURRENT_USER_OBJ = user;
                LoginOutputData loginOutputData = new LoginOutputData(user.getId(), false);
                userPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
