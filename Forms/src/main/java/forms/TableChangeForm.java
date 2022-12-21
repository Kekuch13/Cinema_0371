package forms;

import java.util.ArrayList;

public class TableChangeForm {
    final String form = "ChangeTable";

    public String action;
    public int[] rowIndex;



    public TableChangeForm(String action, int[] rowIndex) {
        this.action = action;
        this.rowIndex = rowIndex;
    }

    public String getForm() {
        return form;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int[] getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int[] rowIndex) {
        this.rowIndex = rowIndex;
    }
}
