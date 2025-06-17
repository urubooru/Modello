package gui;

import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HUDPartecipante {
    private JComboBox hackathonComboBox;
//    private JButton refreshButton;
    private JComboBox userComboBox;
    private JButton inviteButton;
    private JTextArea descrizioneDocumento;
    private JButton submitButton;
    private JTextField teamName;
    private JButton creaButton;
    public JPanel partecipantePanel;

    private HackathonController controller;

    public HUDPartecipante(HackathonController c) {
        JFrame partecipanteFrame = new JFrame("GUI partecipante");
        partecipanteFrame.setContentPane(partecipantePanel);
        partecipanteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        partecipanteFrame.pack();
        partecipanteFrame.setLocationRelativeTo(null);
        partecipanteFrame.setVisible(true);

        this.controller = c;

        hackathonComboBox.addItem("");
        for(String titolo : c.getTitoloHackathons(c.getHackathons())) {
            hackathonComboBox.addItem(titolo);
        }

        userComboBox.removeAllItems();
        userComboBox.addItem("");
        for(String user : c.getUserNames()) {
            if(!user.equals(c.getUsername())) {
                userComboBox.addItem(user);
            }
        }

        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c.creaTeam(teamName.getText(), hackathonComboBox.getSelectedItem());
                    JOptionPane.showMessageDialog(partecipanteFrame, "Team creato!");
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(partecipanteFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        inviteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    c.invita(hackathonComboBox.getSelectedItem(),userComboBox.getSelectedItem());
                    JOptionPane.showMessageDialog(partecipanteFrame, "Ho invitato l'utente " + userComboBox.getSelectedItem().toString() + " nel tuo team!");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(partecipanteFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

//        refreshButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                populateComboBox(c);
//            }
//        });
    }
}
