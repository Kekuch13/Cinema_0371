package Client.UserFramePanels;

import Entities.Film;
import Entities.Session;

import javax.swing.*;
import java.awt.*;

public class SessionsPanel extends JPanel  {
    Film film;
    public JList<Session> sessionList;
    public DefaultListModel<Session> sessionModel = new DefaultListModel<>();
    public JLabel film_title;

    public SessionsPanel() {
        this.setSize(800, 600);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        sessionList = new JList<>();
        sessionList.setModel(sessionModel);
        sessionList.setBackground(new Color(255, 178, 102));
        sessionList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        sessionList.setFixedCellHeight(150);
        sessionList.setFixedCellWidth(195);
        sessionList.setVisibleRowCount(0);
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer) sessionList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        film_title = new JLabel();
        film_title.setPreferredSize(new Dimension(100, 40));

        this.setBackground(Color.lightGray);
        this.add(film_title);
        this.add(new JScrollPane(sessionList));
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
        film_title.setText(film.getTitle());
    }
}
