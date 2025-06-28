package gui;

import controller.HackathonController;

import javax.swing.*;

public class Problema {
    private JLabel problema;
    public JPanel problemaPanel;

    HackathonController controller;

    public Problema(HackathonController con, String content) {
        JFrame problemaFrame = new JFrame("Problema");
        problemaFrame.setContentPane(problemaPanel);
        problemaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        problemaFrame.pack();
        problemaFrame.setLocationRelativeTo(null);
        problemaFrame.setVisible(true);

        //Non si resizava in automatico la finestra
        problemaFrame.setSize(500, 250);

        controller = con;

        if(content == null || content.isEmpty()) {
            problema.setText("Problema vuoto o non pubblicato");
        } else {
            problema.setText(content);
        }
    }
}
