package view;

import data_access.User.userDataAccessObject;
import interface_adapter.Login.loginController;
import interface_adapter.Login.loginPresenter;
import interface_adapter.Login.loginState;
import interface_adapter.Login.loginViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signUp.signUpController;
import interface_adapter.signUp.signUpPresenter;
import interface_adapter.signUp.signUpState;
import interface_adapter.signUp.signUpViewModel;
import use_case.login.LoginInteractor;
import use_case.signUp.signUpInteractor;

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

    public LoginView(loginViewModel LVM, loginController LController) {
        this.LVM = LVM;
        this.LController = LController;
        JFrame frame = new JFrame("Login");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel){
        LVM.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        JButton login = new JButton(LVM.LOGIN_BUTTON_LABEL);
        buttons.add(login);

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

    public static void main(String[] args) {
        loginViewModel s = new loginViewModel();
        userDataAccessObject d = new userDataAccessObject();
        ViewManagerModel vmm = new ViewManagerModel();
        loginPresenter p = new loginPresenter(s, vmm, "main");
        LoginInteractor i = new LoginInteractor(d, p);
        loginController c = new loginController(i);
        new LoginView(s, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        loginState state = (loginState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}
