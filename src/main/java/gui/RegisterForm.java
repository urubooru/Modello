package gui;

import Model.Utente;
import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm {
    private HackathonController controller;
    private JLabel registerUsername;
    private JLabel registerPassword;
    private JTextField registerUserTxt;
    private JTextField registerPassTxt;
    public JPanel registerForm;
    private JButton createUser;
    private JTextField emailTxt;

    public RegisterForm(HackathonController c) {
        JFrame regFrame = new JFrame("Register");
        regFrame.setContentPane(registerForm);
        regFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        regFrame.pack();
        regFrame.setLocationRelativeTo(null);
        regFrame.setVisible(true);

        this.controller = c;

        createUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailTxt.getText(); String user = registerUserTxt.getText(); String pass = registerPassTxt.getText();
                try {
                    Utente u = new Utente(email, user, pass);
                    controller.addUtente(u);
                    JOptionPane.showMessageDialog(registerForm, "Utente registrato");
                } catch (RuntimeException e1) {
                    JOptionPane.showMessageDialog(registerForm, "Error: " + e1.getMessage(), "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

}
