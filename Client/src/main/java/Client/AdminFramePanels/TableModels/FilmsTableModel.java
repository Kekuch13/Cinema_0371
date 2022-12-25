package Client.AdminFramePanels.TableModels;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class FilmsTableModel extends AbstractTableModel {

    private final int columnCount = 6;
    private final ArrayList<String[]> dataArrayList;

    public FilmsTableModel() {
        dataArrayList = new ArrayList<String[]>();
        for (int i = 0; i < dataArrayList.size(); i++) {
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
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Название";
            case 2:
                return "Длительность";
            case 3:
                return "Жанр";
            case 4:
                return "Год";
            case 5:
                return "Страна";
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
        for (int i = 0; i < selectedRows.length; i++) {
            selectedID[i] = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
        }
        return selectedID;
    }

    public void addData(String[] row) {
        String[] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }
}
