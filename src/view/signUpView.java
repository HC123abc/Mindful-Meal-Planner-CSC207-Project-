package view;

import interface_adapter.signUp.SignUpController;
import interface_adapter.signUp.SignUpState;
import interface_adapter.signUp.SignUpViewModel;

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

public class signUpView extends JPanel implements ActionListener, PropertyChangeListener {
    private final SignUpViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField checkPasswordInputField = new JPasswordField(15);
    private final SignUpController signupController;
    private final interface_adapter.Login.LoginController LoginController;
    private String viewName = "Sign Up";
    private String instructionsText;
    JLabel instructions = new JLabel(instructionsText);


    public signUpView(SignUpController controller, SignUpViewModel signupViewModel, interface_adapter.Login.LoginController loginController){
        this.signupViewModel = signupViewModel;
        this.signupController = controller;
        this.LoginController = loginController;
        this.instructionsText = "🪿 Please input a username. 🪿";

        // Create a panel to hold the components
        JPanel panel = new JPanel(new FlowLayout());
        placeComponents(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Using BoxLayout for vertical arrangement
        add(panel);
        setVisible(true);
        this.viewName = signupViewModel.getViewName();

    }

    private void placeComponents(JPanel panel){
        Color green = new Color(177, 214, 171);
        this.setBackground(green);
        signupViewModel.addPropertyChangeListener(this);
        //GridLayout layout = new GridLayout(2, 1);
        //this.setLayout(layout);

        //title
        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("", Font.PLAIN, 24));
        JPanel title = new JPanel();
        title.setBackground(green);
        title.add(titleLabel); // temp title
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

        buttonFactory but = new buttonFactory();
        JPanel buttons = new JPanel();
        JButton signUp = but.makeButton("Sign Up", 12);
        buttons.add(signUp);
        JButton login = but.makeButton("To Login", 12);
        buttons.add(login);
        JPanel important = new JPanel();
        JButton honk = but.makeButton("🪿", 12);
        important.add(honk);
        buttons.setBackground(green);
        important.setBackground(green); // honk

        JPanel instruction = new JPanel();
        instruction.add(this.instructions);
        this.instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instruction.setBackground(green);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignUpViewModel.USERNAME_LABEL), usernameInputField, green);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignUpViewModel.PASSWORD_LABEL), passwordInputField, green);
        LabelTextPanel checkPasswordInfo = new LabelTextPanel(
                new JLabel(SignUpViewModel.CHECK_PASSWORD_LABEL), checkPasswordInputField, green);

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
                            SignUpState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getCheckPassword()
                            );

                        }
                    }
                }
        );

        honk.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(honk)) {
                            System.out.println("Honk!");
                            instructions.setText("🪿Honk!🪿");
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
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
                        SignUpState currentState = signupViewModel.getState();
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
                        SignUpState currentState = signupViewModel.getState();
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
        panel.add(instruction);
        panel.add(important);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignUpState state = (SignUpState) evt.getNewValue();
        System.out.println(state.getError());;
        if (state.getError() != null) {
            new errors().showError(this, state.getError(), "Error");
        }
        if (state.getEmpty() != null){
            instructions.setText(instructionsText);
        }
    }
}
