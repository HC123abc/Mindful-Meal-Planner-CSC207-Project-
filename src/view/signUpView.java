package view;

import data_access.User.userDataAccessObject;
import interface_adapter.Login.loginViewModel;
import interface_adapter.signUp.signUpController;
import interface_adapter.signUp.signUpPresenter;
import interface_adapter.signUp.signUpState;
import interface_adapter.signUp.signUpViewModel;
import interface_adapter.ViewManagerModel;
import use_case.signUp.signUpInteractor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class signUpView extends JPanel implements ActionListener, PropertyChangeListener {
    private final signUpViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField checkPasswordInputField = new JPasswordField(15);
    private final signUpController signupController;

    public signUpView(signUpController controller, signUpViewModel signupViewModel){
        this.signupViewModel = signupViewModel;
        this.signupController = controller;

        //Visibility of frame
        JFrame frame = new JFrame("SignUp");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);

    }

    private void placeComponents(JPanel panel){
        signupViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        JButton signUp = new JButton(signUpViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(signUpViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(signUpViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel checkPasswordInfo = new LabelTextPanel(
                new JLabel(signUpViewModel.CHECK_PASSWORD_LABEL), checkPasswordInputField);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            signUpState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getCheckPassword()
                            );

                        }
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signUpState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signUpState  currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        checkPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signUpState  currentState = signupViewModel.getState();
                        currentState.setCheckPassword(checkPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        panel.add(usernameInfo);
        panel.add(passwordInfo);
        panel.add(checkPasswordInfo);
        panel.add(buttons);

    }

    public static void main(String[] args) {
        signUpViewModel s = new signUpViewModel();
        userDataAccessObject d = new userDataAccessObject();
        loginViewModel l = new loginViewModel();
        ViewManagerModel vmm = new ViewManagerModel();
        signUpPresenter p = new signUpPresenter(s, l, vmm);
        signUpInteractor i = new signUpInteractor(d, p);
        signUpController c = new signUpController(i);
        new signUpView(c, s);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        signUpState state = (signUpState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}
