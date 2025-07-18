package gui;

import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginForm {
    private JPanel loginPanel;
    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JButton entraButton;
    private JButton registratiButton;

    private final HackathonController c;

    static JFrame frame = new JFrame("Login");

    public static void main(String[] args) throws SQLException {
        frame.setContentPane(new LoginForm().loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public LoginForm() throws SQLException {
        this.c = new HackathonController();

        entraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c.login(userTextField.getText(), passwordField.getText());
                    UserInterface userInt = new UserInterface(c, frame);
                    userInt.userInterface.setVisible(true);
                    frame.setVisible(false);
                } catch (RuntimeException e1) {
                    JOptionPane.showMessageDialog(loginPanel, "Error: " + e1.getMessage(), "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm regForm = new RegisterForm(c);
                regForm.registerForm.setVisible(true);
            }
        });
    }
}
