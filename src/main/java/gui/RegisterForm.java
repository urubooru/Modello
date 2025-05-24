package gui;

import javax.swing.*;

public class RegisterForm {
    private JLabel registerUsername;
    private JLabel registerPassword;
    private JTextField registerUserTxt;
    private JTextField registerPassTxt;
    public JPanel registerForm;
    private JButton createUser;

    public RegisterForm() {
        JFrame regFrame = new JFrame("Register");
        regFrame.setContentPane(registerForm);
        regFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        regFrame.pack();
        regFrame.setLocationRelativeTo(null);
        regFrame.setVisible(true);

    }
}
