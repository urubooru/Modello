package gui;

import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public HUDGiudice(HackathonController c) {
        controller = c;

        JFrame giudiceFrame = new JFrame("GUI Organizzatore");
        giudiceFrame.setContentPane(giudicePanel);
        giudiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        giudiceFrame.pack();
        giudiceFrame.setLocationRelativeTo(null);
        giudiceFrame.setVisible(true);

        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //documentiTeam docGui = new documentiTeam(c, utente, teams.getSelectedItem());
                //docGui.documentiPanel.setVisible(true);
            }
        });
    }
}
