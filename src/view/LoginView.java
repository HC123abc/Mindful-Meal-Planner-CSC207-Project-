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
import java.awt.*;
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
        Color green = new Color(177, 214, 171);
        this.setBackground(green);
        LVM.addPropertyChangeListener(this);
        JPanel buttons = new JPanel();
        JButton login = new JButton(LVM.LOGIN_BUTTON_LABEL);
        JButton signUp = new JButton("Sign Up");
        login.setFont(new Font("", Font.PLAIN, 12));
        signUp.setFont(new Font("", Font.PLAIN, 12));
        //login.setBorder(BorderFactory.createEtchedBorder(2,Color.black, Color.GRAY));
        //signUp.setBorder(BorderFactory.createEtchedBorder(2,Color.black, Color.GRAY));
        buttons.add(login);
        buttons.add(signUp);
        buttons.setBackground(green);

        JLabel usernameLabel = new JLabel(LVM.USERNAME_LABEL);
        usernameLabel.setFont(new Font("", Font.PLAIN , 16));
        usernameLabel.setBackground(green);

        JLabel passwordLabel = new JLabel(LVM.PASSWORD_LABEL);
        passwordLabel.setFont(new Font("", Font.PLAIN, 16));
        passwordLabel.setBackground(green);

        JLabel titleLabel = new JLabel("Mindful Meal Preparer");
        titleLabel.setFont(new Font("", Font.PLAIN, 24));
        JPanel title = new JPanel();
        title.setBackground(green);
        title.add(titleLabel); // temp title
        panel.add(title);
        panel.add(Box.createHorizontalStrut(100)); // need a seperator, idk how



        LabelTextPanel usernameInfo = new LabelTextPanel(
                usernameLabel, usernameInputField, green);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                passwordLabel, passwordInputField, green);

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
        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
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
