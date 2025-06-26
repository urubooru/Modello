package gui;

import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HUDGiudice {
    public JPanel hackathonPanel;
    private JComboBox hackathonComboBox;
    private JTextField problemDescription;
    private JButton pubblicaButton;
    private JComboBox teams;
    private JTextField votoText;
    private JButton votaButton;
    private JButton visualizzaButton;
    public JPanel giudicePanel;
    private JLabel pubblicaProblemaLabel;
    private JLabel votoLabel;

    private HackathonController controller;

    public HUDGiudice(HackathonController c) {
        controller = c;

        JFrame giudiceFrame = new JFrame("GUI Giudice");
        giudiceFrame.setContentPane(giudicePanel);
        giudiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        giudiceFrame.pack();
        giudiceFrame.setLocationRelativeTo(null);
        giudiceFrame.setVisible(true);

        hackathonComboBox.addItem("");
        c.populateHackathonBoxGiudice(hackathonComboBox);

        pubblicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    c.setProblema(hackathonComboBox.getSelectedItem(), problemDescription.getText());
                    JOptionPane.showMessageDialog(giudiceFrame, "Problema pubblicato!");
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(giudiceFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //documentiTeam docGui = new documentiTeam(c, utente, teams.getSelectedItem());
                //docGui.documentiPanel.setVisible(true);
            }
        });

        hackathonComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.populateTeamComboBox(hackathonComboBox.getSelectedItem(), teams);
            }
        });

        votaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    c.votaTeam(hackathonComboBox.getSelectedItem(), teams.getSelectedItem(), votoText.getText());
                    JOptionPane.showMessageDialog(giudiceFrame, "Voto dato!");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(giudiceFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
