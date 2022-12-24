package Client.UserFramePanels;

import Entities.Film;
import Entities.Session;
import Entities.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class TicketsPanel extends JPanel {
    Film film;
    Session session;
    JLabel film_title;
    ArrayList<JButton> btns = new ArrayList<JButton>();
    ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    Map<JButton, Integer> tr;

    public TicketsPanel () {
        this.setSize(800, 600);
        this.setLayout(null);

        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 10; ++j) {
                JButton btn = new JButton();
                btn.setBounds(j*50+10, i*50+100, 40, 40);
                btn.setFont(new Font("Arial", Font.BOLD, 16));
                btn.setBackground(Color.BLUE);
                this.add(btn);
                btns.add(btn);
            }
        }
        film_title = new JLabel();
        film_title.setPreferredSize(new Dimension(100, 40));

        this.add(film_title);
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
        film_title.setText(film.getTitle());
    }
}
