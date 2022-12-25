package Client;

import Client.UserFramePanels.*;
import Entities.Film;
import Entities.Session;
import com.google.gson.Gson;
import forms.BookingForm;
import forms.FilmsForm;
import forms.SessionsForm;
import forms.TicketsForm;

import javax.swing.*;
import java.awt.*;
import java.awt.print.Book;
import java.util.ArrayList;

public class UserFrame extends JFrame {
    private ClientConnection Conn;
    private Gson gson = new Gson();
    private JPanel mainPanel;
    private FilmsPanel filmsPanel;
    private SessionsPanel sessionsPanel;
    private TicketsPanel ticketsPanel;
    private CardLayout cl = new CardLayout();

    public UserFrame() {
        Conn = ClientConnection.instance;

        mainPanel = new JPanel();
        mainPanel.setSize(800, 600);
        mainPanel.setLayout(cl);

        sessionsPanel = new SessionsPanel();

        ticketsPanel = new TicketsPanel();

        filmsPanel = new FilmsPanel();
        FilmsForm filmsForm = getFilmsForm();
        for (int i = 0; i < filmsForm.films.size(); ++i) {
            filmsPanel.filmsModel.addElement(filmsForm.films.get(i));
        }
        filmsPanel.filmList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Film film = filmsPanel.filmList.getSelectedValue();
                if (film != null) {
                    sessionsPanel.setFilm(film);

                    sessionsPanel.sessionModel.removeAllElements();
                    SessionsForm sessionsForm = getSessionsForm(film);
                    for (int i = 0; i < sessionsForm.sessions.size(); ++i) {
                        sessionsPanel.sessionModel.addElement(sessionsForm.sessions.get(i));
                    }
                    cl.show(mainPanel, "2");
                }
            }
        });

        sessionsPanel.sessionList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Session session = sessionsPanel.sessionList.getSelectedValue();
                if (session != null) {
                    TicketsForm ticketsForm = getTicketsForm(session);
                    ticketsPanel.setFilm(sessionsPanel.getFilm());
                    ticketsPanel.setTickets(ticketsForm.getTickets());
                    cl.show(mainPanel, "3");
                }
            }
        });

        sessionsPanel.back.addActionListener(arg0 -> {
            cl.show(mainPanel, "1");
            int idx = filmsPanel.filmList.getSelectedIndex();
            filmsPanel.filmList.removeSelectionInterval(idx, idx);
        });

        ticketsPanel.back.addActionListener(e -> {
            cl.show(mainPanel, "2");
            int idx = sessionsPanel.sessionList.getSelectedIndex();
            sessionsPanel.sessionList.removeSelectionInterval(idx, idx);
        });
        ticketsPanel.save.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(ticketsPanel, "Вы уверены?", "Подтверждение", JOptionPane.YES_NO_OPTION);
            if(op == 0 && !ticketsPanel.selected.isEmpty()) {
                bookTickets(ticketsPanel.selected);
                cl.show(mainPanel, "1");
                int idx = filmsPanel.filmList.getSelectedIndex();
                filmsPanel.filmList.removeSelectionInterval(idx, idx);
            }
        });

        mainPanel.add(filmsPanel, "1");
        mainPanel.add(sessionsPanel, "2");
        mainPanel.add(ticketsPanel, "3");
        cl.show(mainPanel, "1");

        this.getContentPane().add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Cinema_0371");
        this.setLocationRelativeTo(null);
        this.setLayout(cl);
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
        String json = gson.toJson(sessionsForm);
        Conn.sendToServer(json);
        String line = Conn.receiveFromServer();
        sessionsForm = gson.fromJson(line, SessionsForm.class);

        return sessionsForm;
    }

    public TicketsForm getTicketsForm (Session session) {
        TicketsForm ticketsForm = new TicketsForm(session.getDate(), session.getTime(), session.getHall_id(), null);
        String json = gson.toJson(ticketsForm);
        Conn.sendToServer(json);
        String line = Conn.receiveFromServer();
        ticketsForm = gson.fromJson(line, TicketsForm.class);

        return ticketsForm;
    }

    public void bookTickets(ArrayList<Integer> selected) {
        BookingForm bookingForm = new BookingForm(selected);
        String json = gson.toJson(bookingForm);
        Conn.sendToServer(json);
    }
}
