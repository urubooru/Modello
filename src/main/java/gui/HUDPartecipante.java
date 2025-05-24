package gui;

import Model.Hackathon;
import Model.Utente;
import controller.HackathonController;

import javax.swing.*;

public class HUDPartecipante {
    private JComboBox hackathonComboBox;
    private JButton refreshButton;
    private JComboBox userComboBox;
    private JButton inviteButton;
    private JTextArea descrizioneDocumento;
    private JButton submitButton;
    private JTextField teamName;
    private JButton creaButton;
    public JPanel partecipantePanel;

    private HackathonController controller;

    public HUDPartecipante(HackathonController c, Utente utente) {
        JFrame partecipanteFrame = new JFrame("GUI partecipante");
        partecipanteFrame.setContentPane(partecipantePanel);
        partecipanteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        partecipanteFrame.pack();
        partecipanteFrame.setLocationRelativeTo(null);
        partecipanteFrame.setVisible(true);

        this.controller = c;

    }
}
