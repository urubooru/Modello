package gui;

import Model.Utente;
import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
    private JTextField newpassTxtF;
    private JButton CAMBIAButton;
    private JButton REFRESHButton;

    private HackathonController controller;

    public UserInterface(HackathonController c, JFrame callframe, Utente utente) {
        JFrame userFrame = new JFrame("UserInterface");
        userFrame.setContentPane(userInterface);
        userFrame.pack();
        userFrame.setLocationRelativeTo(null);
        userFrame.setVisible(true);

        this.controller = c;

        hackathons.addItem("");
        for(String titolo : c.getTitoloHackathons(c.getHackathons())) {
            hackathons.addItem(titolo);
        }

        userFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent){
                userFrame.setVisible(false);
                callframe.setVisible(true);
                userFrame.dispose();
            }
        });

        CAMBIAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                try {
                    utente.setPassword(newpassTxtF.getText());
                    JOptionPane.showMessageDialog(userInterface, "Password cambiata");
                } catch(RuntimeException e){
                    JOptionPane.showMessageDialog(userInterface, "Error: " + e.getMessage(), "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        REFRESHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    ArrayList<String> listOfTeams = c.getTeamClassifica(c.getClassifica(hackathons.getSelectedItem().toString()));
                    ArrayList<Integer> voti = c.getVotiClassifica(c.getClassifica(hackathons.getSelectedItem().toString()));

                    System.out.println("Prova");
                    TableClassificaModel tableModel = new TableClassificaModel();
                    hackathonTable.setModel(tableModel);
                    if(!listOfTeams.isEmpty()){
                        tableModel.setTeamNames(listOfTeams);
                        tableModel.setVotes(voti);
                        tableModel.fireTableDataChanged();
                    } else{
                        tableModel.setTeamNames(new ArrayList<>());
                        tableModel.setVotes(new ArrayList<>());
                        tableModel.fireTableDataChanged();
                    }
                } catch(RuntimeException e){
                    JOptionPane.showMessageDialog(userInterface, "Error: " + e.getMessage(), "Error:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        partecipanteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                HUDPartecipante hudPartecipante = new HUDPartecipante(c, utente);
                hudPartecipante.partecipantePanel.setVisible(true);
            }
        });

        organizzatoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                HUDOrganizzatore hudOrganizzatore = new HUDOrganizzatore(c, utente);
                hudOrganizzatore.organizzatorePanel.setVisible(true);
            }
        });

        giudiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                HUDGiudice hudGiudice = new HUDGiudice(c, utente);
                hudGiudice.giudicePanel.setVisible(true);
                hudGiudice.hackathonPanel.setVisible(true);
            }
        });
    }
}
