package gui;

import controller.HackathonController;
import model.Partecipante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentiCommenti {
    public JPanel documentiCommentiPanel;
    private JComboBox dataComboBox;
    private JLabel content;
    private JLabel contentLabel;
    private JLabel giudiceLabel;
    private JComboBox giudiceComboBox;
    private JLabel commento;
    private JLabel commentoLabel;

    HackathonController controller;

    public DocumentiCommenti(HackathonController c, Object hackathon) {
        if(hackathon==null || hackathon.toString().isEmpty()){
            throw new RuntimeException("Hackathon errato");
        }
        String hackathonName = hackathon.toString();

        controller = c;

        JFrame documentiCommentiFrame = new JFrame("Visualizza commenti");
        documentiCommentiFrame.setContentPane(documentiCommentiPanel);
        documentiCommentiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        documentiCommentiFrame.pack();
        documentiCommentiFrame.setLocationRelativeTo(null);
        documentiCommentiFrame.setVisible(true);
        documentiCommentiFrame.setSize(500,250);

        String teamName = controller.getTeam(hackathonName);

        System.out.println(teamName + " " + hackathonName);
        controller.populateDataComboBox(dataComboBox, teamName, hackathonName);

        dataComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.setText(controller.getDescDoc(dataComboBox.getSelectedItem(), teamName, hackathonName));
                controller.populateDocumentiBox(giudiceComboBox, teamName, hackathonName, dataComboBox.getSelectedItem());
            }
        });

        giudiceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commento.setText(controller.getDescComm(dataComboBox.getSelectedItem(), teamName, hackathonName, giudiceComboBox.getSelectedItem()));
            }
        });
    }
}
