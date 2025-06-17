package gui;

import controller.HackathonController;

import javax.swing.*;

public class HUDOrganizzatore {
    public JPanel organizzatorePanel;
    private JPanel hackathonPanel;
    private JComboBox hackComboBox;
    private JButton refreshButton;
    private JButton publish;
    private JLabel apriRegistrazioniLabel;
    private JButton apriButton;
    private JComboBox userList;
    private JButton invitaButton;

    HackathonController controller;

    public HUDOrganizzatore(HackathonController c) {
        controller = c;

        JFrame organizzatoreFrame = new JFrame("GUI Organizzatore");
        organizzatoreFrame.setContentPane(organizzatorePanel);
        organizzatoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        organizzatoreFrame.pack();
        organizzatoreFrame.setLocationRelativeTo(null);
        organizzatoreFrame.setVisible(true);
    }
}
