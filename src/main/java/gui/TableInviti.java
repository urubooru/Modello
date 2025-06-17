package gui;

import model.Invito;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;

public class TableInviti extends AbstractTableModel {

    private ArrayList<Invito> hackathonDiInviti;
    public void setData(ArrayList<Invito> hackathonDiInviti) {
        this.hackathonDiInviti = hackathonDiInviti;
    }

    private String[] columnNames = {"Hackathon","Team"};

    @Override
    public int getRowCount() {
        if(hackathonDiInviti!=null){
            return hackathonDiInviti.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        Invito i = hackathonDiInviti.get(rowIndex);
        switch(columnIndex){
            case 0:
                return i.getHackathon().getTitolo();
            case 1:
                if(i.getTeam()!=null) {
                    return i.getTeam().getNome();
                }
                else {
                    return "ORGANIZZATORE";
                }
        }

        return null;
    }

}
