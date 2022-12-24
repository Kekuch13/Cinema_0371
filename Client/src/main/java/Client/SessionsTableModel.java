package Client;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SessionsTableModel extends AbstractTableModel {
    private int columnCount = 3;
    private ArrayList<String []> dataArrayList;

    public SessionsTableModel() {
        dataArrayList = new ArrayList<String []>();
        for (int i = 0; i < dataArrayList.size(); i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0: return "Дата";
            case 1: return "Время начала";
            case 2: return "Зал";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public int[] getSelectedID(int[] selectedRows) {
        int[] selectedID = new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++){
            selectedID[i] = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
        }
        return selectedID;
    }

    public void addData(String [] row){
        String [] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }
}
