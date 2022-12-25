package Client.UserFramePanels;

import Entities.Film;
import Entities.Session;
import Entities.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TicketsPanel extends JPanel {
    Film film;
    Session session;
    JLabel film_title, session_title, screen;
    public JButton back, save;
    public Map<JButton, Ticket> ticketsBtns = new LinkedHashMap<JButton, Ticket>();
    public ArrayList<Integer> selected = new ArrayList<Integer>();


    public TicketsPanel() {
        this.setSize(800, 600);
        this.setLayout(null);
        this.setBackground(new Color(255, 178, 102));

        for (int i = 0; i < 5; ++i) {
            JLabel row = new JLabel("ряд " + String.valueOf(i+1));
            row.setBounds(105, i * 50 + 170, 60, 40);
            row.setFont(new Font("Arial", Font.BOLD, 16));
            this.add(row);
            for (int j = 0; j < 10; ++j) {
                JButton btn = new JButton();
                btn.setBounds(j * 50 + 165, i * 50 + 170, 40, 40);
                btn.setBackground(new Color(65, 65, 255));
                btn.addActionListener(e -> {
                    if(!ticketsBtns.get(btn).is_sold()) {
                        if(selected.contains(ticketsBtns.get(btn).getTicket_id())) {
                            btn.setBackground(new Color(65, 65, 255));
                            selected.remove(selected.indexOf(ticketsBtns.get(btn).getTicket_id()));
                        } else {
                            btn.setBackground(new Color(55, 255, 0));
                            selected.add(ticketsBtns.get(btn).getTicket_id());
                        }
                    }
                });
                ticketsBtns.put(btn, null);
                this.add(btn);
            }
        }
        for(int i = 0; i < 10; ++i) {
            JLabel col = new JLabel(String.valueOf(i+1), SwingConstants.CENTER);
            col.setBounds(i * 50 + 165, 410, 40, 40);
            col.setFont(new Font("Arial", Font.BOLD, 16));
            this.add(col);
        }

        film_title = new JLabel("", SwingConstants.CENTER);
        film_title.setBounds(0, 10, 800, 40);
        film_title.setFont(new Font("Arial", Font.BOLD, 20));

        session_title = new JLabel("", SwingConstants.CENTER);
        session_title.setBounds(250, 40, 300, 40);
        session_title.setFont(new Font("Arial", Font.BOLD, 20));

        back = new JButton("Назад");
        back.setBounds(165, 500, 100, 40);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.gray);
        back.setForeground(Color.black);

        save = new JButton("Подтвердить");
        save.setBounds(505, 500, 150, 40);
        save.setFont(new Font("Arial", Font.BOLD, 16));
        save.setBackground(new Color(170, 65, 255));
        save.setForeground(Color.black);

        screen = new JLabel("Экран", SwingConstants.CENTER);
        screen.setFont(new Font("Arial", Font.BOLD, 16));
        screen.setOpaque(true);
        screen.setBackground(Color.GRAY);
        screen.setBounds(135, 120, 550, 25);

        this.add(film_title);
        this.add(session_title);
        this.add(back);
        this.add(save);
        this.add(screen);
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        if (tickets.size() != 50) {
            System.out.println("БИЛЕТОВ ПОЧЕМУ-ТО НЕ 50шт.");
            System.exit(0);
        }
        selected.clear();
        int idx = 0;
        for (Map.Entry<JButton, Ticket> pair : ticketsBtns.entrySet()) {
            pair.setValue(tickets.get(idx));
            if (tickets.get(idx).is_sold()) pair.getKey().setBackground(Color.red);
            else pair.getKey().setBackground(new Color(65, 65, 255));
            idx++;
        }
        session_title.setText(tickets.get(0).getDate() + "     " + tickets.get(0).getTime());
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
        film_title.setText(film.getTitle() + " (" + film.getDuration() + " мин)");
    }
}
