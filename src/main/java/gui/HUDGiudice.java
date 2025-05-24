package gui;

import Model.Utente;
import controller.HackathonController;

import javax.swing.*;

public class HUDGiudice {
    public JPanel hackathonPanel;
    private JComboBox comboBox1;
    private JButton refreshButton;
    private JTextField problemDescription;
    private JButton pubblicaButton;
    private JComboBox teams;
    private JTextField textField1;
    private JButton votaButton;
    private JButton visualizzaButton;
    public JPanel giudicePanel;

    private HackathonController controller;

    public HUDGiudice(HackathonController c, Utente utente) {
        controller = c;

        JFrame giudiceFrame = new JFrame("GUI Organizzatore");
        giudiceFrame.setContentPane(giudicePanel);
        giudiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        giudiceFrame.pack();
        giudiceFrame.setLocationRelativeTo(null);
        giudiceFrame.setVisible(true);
    }
}
