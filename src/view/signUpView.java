package view;

import interface_adapter.Login.loginController;
import interface_adapter.signUp.signUpController;
import interface_adapter.signUp.signUpState;
import interface_adapter.signUp.signUpViewModel;

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
    private final loginController LoginController;
    private String viewName = "Sign Up";
    private String instructionsText;

    public signUpView(signUpController controller, signUpViewModel signupViewModel, loginController loginController){
        this.signupViewModel = signupViewModel;
        this.signupController = controller;
        this.LoginController = loginController;
        this.instructionsText = "Please input a username. 🪿";

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        placeComponents(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Using BoxLayout for vertical arrangement
        add(panel);
        setVisible(true);
        this.viewName = signupViewModel.getViewName();

    }

    private void placeComponents(JPanel panel){
        signupViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        JButton signUp = new JButton(signUpViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        JButton login = new JButton("To Login");
        buttons.add(login);

        JLabel instructions = new JLabel(instructionsText);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(signUpViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(signUpViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel checkPasswordInfo = new LabelTextPanel(
                new JLabel(signUpViewModel.CHECK_PASSWORD_LABEL), checkPasswordInputField);

        login.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            LoginController.execute("", "");
                        }
                    }
                }
        );

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
        panel.add(instructions);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        signUpState state = (signUpState) evt.getNewValue();
        System.out.println(state.getError());;
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
        if (state.getEmpty() != null){
            System.out.println("askjhbfkajf!");
            this.instructionsText = "Please Input a Username.";
        }
    }
}
