package gui;

import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
                    controller.creaAggiungiUtente(email, user, pass);
                    JOptionPane.showMessageDialog(registerForm, "Utente registrato");
                } catch (RuntimeException e1) {
                    JOptionPane.showMessageDialog(registerForm, "Error: " + e1.getMessage(), "Error:", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(registerForm, "Errore database: " + ex.getMessage(), "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

}
