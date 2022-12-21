package Client.UserFramePanels;

import Entities.Film;
import Entities.Session;

import javax.swing.*;
import java.util.ArrayList;

public class SessionsPanel extends JPanel  {
    Film film;
    ArrayList<Session> sessions;

    public SessionsPanel() {
        this.setSize(800, 600);
        this.setLayout(null);
    }
}
