package gui;

import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HUDOrganizzatore {
    public JPanel organizzatorePanel;
    private JPanel hackathonPanel;
    private JComboBox hackComboBox;
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

        hackComboBox.addItem("");
        controller.populateHackathonBox(hackComboBox);

        userList.addItem("");
        for(String user : c.getUserNames()) {
            if(!user.equals(c.getUsername())) {
                userList.addItem(user);
            }
        }

        publish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.publishRankings(hackComboBox.getSelectedItem());
                    JOptionPane.showMessageDialog(organizzatoreFrame, "Classifica pubblicate!");
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(organizzatoreFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        apriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.openRegistration(hackComboBox.getSelectedItem());
                    JOptionPane.showMessageDialog(organizzatoreFrame, "Registrazioni aperta!");
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(organizzatoreFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        invitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.invitaGiudice(hackComboBox.getSelectedItem(), userList.getSelectedItem());
                    JOptionPane.showMessageDialog(organizzatoreFrame, "Invito riuscito!");
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(organizzatoreFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
