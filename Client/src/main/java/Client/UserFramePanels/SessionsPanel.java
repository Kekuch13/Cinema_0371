package Client.UserFramePanels;

import Entities.Film;
import Entities.Session;

import javax.swing.*;
import java.awt.*;

public class SessionsPanel extends JPanel {
    public JList<Session> sessionList;
    public DefaultListModel<Session> sessionModel = new DefaultListModel<>();
    public JButton back;
    private JLabel film_title;
    private Film film;

    public SessionsPanel() {
        this.setSize(800, 600);
        this.setBackground(new Color(255, 178, 102));

        sessionList = new JList<>();
        sessionList.setModel(sessionModel);
        sessionList.setPreferredSize(new Dimension(783, 475));
        sessionList.setBackground(new Color(255, 178, 102));
        sessionList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        sessionList.setFixedCellHeight(150);
        sessionList.setFixedCellWidth(195);
        sessionList.setVisibleRowCount(0);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) sessionList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        film_title = new JLabel("", SwingConstants.CENTER);
        film_title.setBounds(0, 10, 800, 40);
        film_title.setFont(new Font("Arial", Font.BOLD, 20));

        back = new JButton("Назад");
        back.setPreferredSize(new Dimension(100, 40));
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.gray);
        back.setForeground(Color.black);

        this.add(film_title);
        this.add(new JScrollPane(sessionList));
        this.add(back);
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
        film_title.setText(film.getTitle() + " (" + film.getDuration() + " мин)");
    }
}
