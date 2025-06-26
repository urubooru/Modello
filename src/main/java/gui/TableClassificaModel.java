package gui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableClassificaModel extends AbstractTableModel {
    private ArrayList<String> teamNames;
    private ArrayList<Integer> votes;

    private String[] columnNames={"Nome team","Voto"};

    public void setTeamNames(ArrayList<String> teamNames) {
        this.teamNames = teamNames;
    }
    public void setVotes(ArrayList<Integer> votes) {
        this.votes = votes;
    }

    @Override
    public int getRowCount() {
        if(!teamNames.isEmpty())
            return teamNames.size();
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return teamNames.get(rowIndex);
            case 1:
                return votes.get(rowIndex);
        }

        return null;
    }
}
