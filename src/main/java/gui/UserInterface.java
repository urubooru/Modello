package gui;

import controller.HackathonController;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserInterface {
    public JPanel userInterface;
    private JPanel buttons;
    private JTable hackathonTable;
    private JButton partecipanteButton;
    private JButton organizzatoreButton;
    private JButton giudiceButton;
    private JComboBox hackathons;
    private JLabel invites;
    private JButton VAIButton;

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
