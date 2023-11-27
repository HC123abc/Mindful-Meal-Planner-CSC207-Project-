package view;

import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final LoginViewModel LVM;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final LoginController LController;
    private interface_adapter.signUp.SignUpController SignUpController;

    public String viewName = "Login";

    public LoginView(LoginViewModel LVM, LoginController LController, interface_adapter.signUp.SignUpController SignUpController) {
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

        //buttons
        JPanel buttons = new JPanel();
        buttonFactory but = new buttonFactory();
        JButton login = but.makeButton(LVM.LOGIN_BUTTON_LABEL, 12);
        JButton signUp = but.makeButton("Sign Up", 12);
        buttons.add(login);
        buttons.add(signUp);
        buttons.setBackground(green);

        //text fields
        JLabel usernameLabel = new JLabel(LVM.USERNAME_LABEL);
        usernameLabel.setFont(new Font("", Font.PLAIN , 16));
        usernameLabel.setBackground(green);

        JLabel passwordLabel = new JLabel(LVM.PASSWORD_LABEL);
        passwordLabel.setFont(new Font("", Font.PLAIN, 16));
        passwordLabel.setBackground(green);

        //title labesl
        JLabel titleLabel = new JLabel("Log In");
        titleLabel.setFont(new Font("", Font.PLAIN, 28));
        JPanel title = new JPanel();
        title.setBackground(green);
        title.add(titleLabel);
        panel.add(title);
        panel.add(Box.createHorizontalStrut(100)); // need a seperator, idk how

        //Logo
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("./assets/logoWName.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaledImage = myPicture.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        JPanel picture = new JPanel();
        picture.add(new JLabel(" "));
        picture.add(picLabel, BorderLayout.CENTER);
        picture.add(new JLabel(" "));
        picture.setBackground(green);
        panel.add(picture);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                usernameLabel, usernameInputField, green);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                passwordLabel, passwordInputField, green);

        login.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            LoginState currentState = LVM.getState();

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
                    LoginState currentState = LVM.getState();
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
                    LoginState currentState = LVM.getState();
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
        LoginState state = (LoginState) evt.getNewValue();
        System.out.println(state.getError() == null);
        if (state.getError() != null) {
            System.out.println(state.getError());
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}
