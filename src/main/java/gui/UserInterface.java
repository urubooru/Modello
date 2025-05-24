package gui;

import Model.Utente;
import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserInterface {
    public JPanel userInterface;
    private JButton buttonP;
    private JButton buttonO;
    private JButton buttonG;

    private HackathonController controller;

    public UserInterface(HackathonController c, JFrame callframe) {
        JFrame userFrame = new JFrame("UserInterface");
        userFrame.setContentPane(userInterface);
        userFrame.pack();
        userFrame.setLocationRelativeTo(null);
        userFrame.setVisible(true);

        userFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent){
                userFrame.setVisible(false);
                callframe.setVisible(true);
                userFrame.dispose();
            }
        });

        this.controller = c;

    }
}
