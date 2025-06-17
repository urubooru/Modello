package gui;

import controller.HackathonController;

import javax.swing.*;

public class GUIInviti {
    private JTable inviteTable;
    private JComboBox Hackathon;
    private JComboBox tipoInvito;
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
    }
}
