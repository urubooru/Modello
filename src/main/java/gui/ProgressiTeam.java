package gui;

import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressiTeam {
    private JComboBox dataComboBox;
    private JTextField commento;
    private JButton commentaButton;
    private JLabel contenutoLabel;
    private JLabel content;


    public JPanel progressiPanel;

    private HackathonController controller;

    public ProgressiTeam(HackathonController con, Object team, Object hackathon) {
        if(team==null || team.toString().isEmpty()) { throw new RuntimeException("Team non valido."); }
        if(hackathon==null || hackathon.toString().isEmpty()) { throw new RuntimeException("Hackathon non valido."); }
        String teamName = team.toString();
        String hackathonName = hackathon.toString();

        JFrame progressiFrame = new JFrame("Problema");
        progressiFrame.setContentPane(progressiPanel);
        progressiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        progressiFrame.pack();
        progressiFrame.setLocationRelativeTo(null);
        progressiFrame.setVisible(true);
        progressiFrame.setSize(500, 250);

        controller = con;
        controller.populateDataComboBox(dataComboBox, teamName, hackathonName);

        dataComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    content.setText(controller.getDescDoc(dataComboBox.getSelectedItem(), teamName, hackathonName));
                } catch(RuntimeException ex){
                    JOptionPane.showMessageDialog(progressiPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        commentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.addCommento(commento.getText(), dataComboBox.getSelectedItem(), hackathonName, teamName);

                    JOptionPane.showMessageDialog(progressiPanel, "Commento aggiunto", "Commento", JOptionPane.INFORMATION_MESSAGE);
                } catch(RuntimeException ex){
                    JOptionPane.showMessageDialog(progressiPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
