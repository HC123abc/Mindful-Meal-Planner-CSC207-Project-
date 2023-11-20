package view;

import data_access.User.userDataAccessObject;
import interface_adapter.Login.loginController;
import interface_adapter.Login.loginPresenter;
import interface_adapter.Login.loginState;
import interface_adapter.Login.loginViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signUp.signUpController;
import use_case.login.LoginInteractor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final loginViewModel LVM;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final loginController LController;
    private signUpController SignUpController;

    public String viewName = "Login";

    public LoginView(loginViewModel LVM, loginController LController, signUpController SignUpController) {
        this.LVM = LVM;
        this.LController = LController;
        this.SignUpController = SignUpController;
        // Create a panel to hold the components
        JPanel panel = new JPanel();
        placeComponents(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Using BoxLayout for vertical arrangement
        add(panel);
        setVisible(true);
        this.viewName = LVM.getViewName();

    }

    private void placeComponents(JPanel panel){
        LVM.addPropertyChangeListener(this);
        JPanel buttons = new JPanel();
        JButton login = new JButton(LVM.LOGIN_BUTTON_LABEL);
        JButton SignUp = new JButton("Sign Up");
        buttons.add(login);
        buttons.add(SignUp);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(LVM.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(LVM.PASSWORD_LABEL), passwordInputField);

        login.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            loginState currentState = LVM.getState();

                            LController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );

                        }
                    }
                }
        );
        SignUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(SignUp)) {
                            SignUpController.execute("","","");
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(
            new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    loginState currentState = LVM.getState();
                    String text = usernameInputField.getText() + e.getKeyChar();
                    currentState.setUsername(text);
                    LVM.setState(currentState);
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
                    loginState currentState = LVM.getState();
                    currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                    LVM.setState(currentState);
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
        panel.add(buttons);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        loginState state = (loginState) evt.getNewValue();
        System.out.println(state.getError() == null);
        if (state.getError() != null) {
            System.out.println(state.getError());
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}
