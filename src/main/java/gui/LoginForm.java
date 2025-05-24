package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JPanel loginPanel;
    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JButton entraButton;
    private JButton registratiButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginForm().loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public LoginForm(){
        registratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm regForm = new RegisterForm();
                regForm.registerForm.setVisible(true);
            }
        });
    }
}
