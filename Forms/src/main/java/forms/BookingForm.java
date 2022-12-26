package forms;

import java.util.ArrayList;

public class BookingForm {
    private final String form = "booking";
    private ArrayList<Integer> tickets_ids;

    public BookingForm(ArrayList<Integer> tickets_ids) {
        this.tickets_ids = tickets_ids;
    }

    public String getForm() {
        return form;
    }

    public ArrayList<Integer> getTickets_ids() {
        return tickets_ids;
    }

    public void setTickets_ids(ArrayList<Integer> tickets_ids) {
        this.tickets_ids = tickets_ids;
    }
}
