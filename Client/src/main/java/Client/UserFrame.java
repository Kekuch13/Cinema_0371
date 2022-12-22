package Client;

import Client.UserFramePanels.*;
import Entities.Film;
import Entities.Session;
import com.google.gson.Gson;
import forms.FilmsForm;
import forms.SessionsForm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame {
    private final ClientConnection Conn;
    private final Gson gson = new Gson();
    private final JPanel mainPanel;
    private final FilmsPanel filmsPanel;
    private final SessionsPanel sessionsPanel;
    private TicketsPanel ticketsPanel;
    private final JButton btn2;
    private final CardLayout cl = new CardLayout();

    // Films
    // Sessions - храним только для выбранного фильма и обновляем при изменении фильма
    // Tickets - храним только для выбранного сеанса и обновляем при смене сеанса

    public UserFrame() {
        Conn = ClientConnection.instance;

        mainPanel = new JPanel();
        mainPanel.setSize(800, 600);
        mainPanel.setLayout(cl);

        btn2 = new JButton("Back");
        btn2.setPreferredSize(new Dimension(100, 40));
        btn2.setFont(new Font("Arial", Font.BOLD, 16));
        btn2.setBackground(Color.white);
        btn2.setForeground(Color.black);

        sessionsPanel = new SessionsPanel();
        sessionsPanel.setBackground(Color.white);
        sessionsPanel.add(btn2);

        filmsPanel = new FilmsPanel();
        FilmsForm filmsForm = getFilmsForm();
        for (int i = 0; i < filmsForm.films.size(); ++i) {
            filmsPanel.filmsModel.addElement(filmsForm.films.get(i));
        }
        filmsPanel.filmList.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                Film film = filmsPanel.filmList.getSelectedValue();
                if(film != null) {
                    sessionsPanel.setFilm(film); // установка ID выбранного фильма

                    sessionsPanel.sessionModel.removeAllElements();

                    SessionsForm sessionsForm = getSessionsForm(film);

                    for (int i = 0; i < sessionsForm.sessions.size(); ++i) {
                        sessionsPanel.sessionModel.addElement(sessionsForm.sessions.get(i));
                    }

                    cl.show(mainPanel, "2");
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(mainPanel, "1");
                int idx = filmsPanel.filmList.getSelectedIndex();
                filmsPanel.filmList.removeSelectionInterval(idx, idx);
            }
        });

        mainPanel.add(filmsPanel, "1");
        mainPanel.add(sessionsPanel, "2");
        cl.show(mainPanel, "1");

        this.getContentPane().add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Cinema_0371");
        this.setLocationRelativeTo(null);
        //this.setLayout(cl);
        this.setVisible(true);
    }

    public FilmsForm getFilmsForm () {
        FilmsForm filmsForm = new FilmsForm(null);
        String json = gson.toJson(filmsForm);
        Conn.sendToServer(json);
        String line = Conn.receiveFromServer();
        filmsForm = gson.fromJson(line, FilmsForm.class);

        return filmsForm;
    }

    public SessionsForm getSessionsForm (Film film) {
        SessionsForm sessionsForm = new SessionsForm(film.getFilm_id(), null);
        String json1 = gson.toJson(sessionsForm);
        Conn.sendToServer(json1);
        String line1 = Conn.receiveFromServer();
        sessionsForm = gson.fromJson(line1, SessionsForm.class);

        return sessionsForm;
    }

    // getTickets - обновляем под каждый сеанс билеты

}
