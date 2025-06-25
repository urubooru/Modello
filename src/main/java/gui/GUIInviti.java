package gui;

import controller.HackathonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIInviti {
    private JTable inviteTable;
    private JComboBox hackathon;
    private JComboBox team;
    private JButton accettaButton;
    private JButton rifiutaButton;
    public JLabel GUIinviti;
    public JPanel guiPanel;

    private HackathonController controller;

    public GUIInviti(HackathonController c) {
        controller = c;

        JFrame invitiFrame = new JFrame("GUI inviti");
        invitiFrame.setContentPane(guiPanel);
        invitiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invitiFrame.pack();
        invitiFrame.setLocationRelativeTo(null);
        invitiFrame.setVisible(true);

        TableInviti modello = new TableInviti();
        inviteTable.setModel(modello);

        modello.setData(controller.getInviti());

        int i = 0;
        this.hackathon.addItem("");
        this.team.addItem("");
        //Doesnt check for dupe values
        while(i < inviteTable.getRowCount()){
            this.hackathon.addItem(inviteTable.getValueAt(i,0));
            this.team.addItem(inviteTable.getValueAt(i,1));
            i++;
        }

        accettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.accettaInvito(hackathon.getSelectedItem(), team.getSelectedItem());
                    modello.clear();
                    modello.setData(controller.getInviti());
                    modello.fireTableDataChanged();
                    JOptionPane.showMessageDialog(invitiFrame, "Invito accettato!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(invitiFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        rifiutaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.rifiutaInvito(hackathon.getSelectedItem(), team.getSelectedItem());
                    modello.clear();
                    modello.setData(controller.getInviti());
                    modello.fireTableDataChanged();
                    JOptionPane.showMessageDialog(invitiFrame, "Invito rifiutato!");
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(invitiFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
