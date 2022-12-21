package Client;

import Client.UserFramePanels.*;
import Entities.Film;
import Entities.Session;
import com.google.gson.Gson;
import forms.FilmsForm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame implements ActionListener {
    private ClientConnection Conn;
    private Gson gson = new Gson();
    private JPanel mainPanel;
    private FilmsPanel filmsPanel;
    private SessionsPanel sessionsPanel;
    private TicketsPanel ticketsPanel;
    private JButton btn2;
    private CardLayout cl = new CardLayout();

    // Films
    // Sessions - храним только для выбранного фильма и обновляем при изменении фильма
    // Tickets - храним только для выбранного сеанса и обновляем при смене сеанса

    public UserFrame() {
        Conn = ClientConnection.instance;

        mainPanel = new JPanel();
        mainPanel.setSize(800, 600);
        mainPanel.setLayout(cl);

        btn2 = new JButton("Back");
        btn2.setPreferredSize(new Dimension(100, 150));
        btn2.setFont(new Font("Arial", Font.BOLD, 16));
        btn2.setBackground(Color.yellow);
        btn2.setForeground(Color.black);

        sessionsPanel = new SessionsPanel();
        sessionsPanel.setBackground(Color.green);
        sessionsPanel.add(btn2);

        FilmsForm filmsForm = new FilmsForm(null);
        String json = gson.toJson(filmsForm);
        Conn.sendToServer(json);
        String line = Conn.receiveFromServer();
        filmsForm = gson.fromJson(line, FilmsForm.class);

        DefaultListModel<Film> kek = new DefaultListModel<>();
        for(int i = 0; i < filmsForm.films.size(); ++i) {
            kek.addElement(filmsForm.films.get(i));
        }
        filmsPanel = new FilmsPanel(kek);
        filmsPanel.filmList.getSelectionModel().addListSelectionListener(e -> {
            Film film = filmsPanel.filmList.getSelectedValue();
            //sessionsPanel.updateList(film.getFilm_id());
            sessionsPanel.setFilm_id(film.getFilm_id()); // установка ID выбранного фильма
            cl.show(mainPanel, "2");
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println(sessionsPanel.getFilm_id()); // вывод ID выбранного фильма в консоль
                cl.show(mainPanel, "1");
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // getFilms
    // getSessions - обновляем под каждый фильм сеансы
    // getTickets - обновляем под каждый сеанс билеты

}
