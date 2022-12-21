package Client.UserFramePanels;

import Client.ClientConnection;
import Entities.Film;
import Entities.Session;
import com.google.gson.Gson;
import forms.FilmsForm;
import forms.SessionsForm;

import javax.swing.*;
import java.awt.*;

public class SessionsPanel extends JPanel  {
    private int film_id;
    private ClientConnection Conn = ClientConnection.instance;
    private Gson gson = new Gson();
    public JList<Session> sessionList;
    public DefaultListModel<Session> sessionModel = new DefaultListModel<>();

    public SessionsPanel() {
        this.setSize(800, 600);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        sessionList = new JList<>();
        sessionList.setModel(sessionModel);
        sessionList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        sessionList.setFixedCellHeight(300);
        sessionList.setFixedCellWidth(200);
        sessionList.setVisibleRowCount(0);
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer) sessionList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        this.add(new JScrollPane(sessionList));
    }

    // ПЕРЕДЕЛАТЬ
    public void updateList(int film_id) {
        sessionModel.removeAllElements();

        SessionsForm sessionsForm = new SessionsForm(film_id, null);
        String json = gson.toJson(sessionsForm);
        System.out.println(json);
        Conn.sendToServer(json);
        String line = Conn.receiveFromServer();
        sessionsForm = gson.fromJson(line, SessionsForm.class);

        DefaultListModel<Session> kek = new DefaultListModel<>();
        for(int i = 0; i < sessionsForm.sessions.size(); ++i) {
            kek.addElement(sessionsForm.sessions.get(i));
        }
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }
}
