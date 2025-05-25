package gui;

import Model.Utente;
import controller.HackathonController;

import javax.swing.*;

public class documentiTeam {
    public JPanel documentiPanel;
    private JTable documentiTable;

    private HackathonController controller;

    public documentiTeam(HackathonController c, Utente u, Object team){
        JFrame partecipanteFrame = new JFrame("GUI partecipante");
        partecipanteFrame.setContentPane(documentiPanel);
        partecipanteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        partecipanteFrame.pack();
        partecipanteFrame.setLocationRelativeTo(null);
        partecipanteFrame.setVisible(true);

        this.controller = c;


    }
}
